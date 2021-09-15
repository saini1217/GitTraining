package customapis;

import java.io.Console;
import java.io.File;
import java.util.logging.Logger;

import org.apache.tools.ant.BuildEvent;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.BuildListener;
import org.apache.tools.ant.DefaultLogger;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectHelper;

import com.provar.core.model.base.api.ValueScope;
import com.provar.core.testapi.ITestExecutionContext;
import com.provar.core.testapi.annotations.TestApi;
import com.provar.core.testapi.annotations.TestApiExecutor;
import com.provar.core.testapi.annotations.TestApiParameter;
import com.provar.core.testapi.annotations.TestApiParameterGroup;
import com.provar.core.testapi.annotations.TestApiParameterGroups;
import com.provar.core.testapi.annotations.TestExecutionContext;
import com.provar.core.testapi.annotations.TestLogger;

@TestApi( title="RunANTTask"
        , summary=""
        , remarks=""
        , iconBase=""
        , defaultApiGroups={"My Test APIs"}
        )
@TestApiParameterGroups(parameterGroups={
      @TestApiParameterGroup(groupName="inputs", title="Inputs"),
      @TestApiParameterGroup(groupName="result", title="Result"),
      })
public class RunANTTask {
  
    @TestApiParameter(seq=1, 
            summary="Path to ANT build file",
            remarks="",
            mandatory=true,
            parameterGroup="inputs")
    public String path;
    
    @TestApiParameter(seq=2, 
            summary="Name of the ANT build file",
            remarks="",
            mandatory=true,
            parameterGroup="inputs")
    public String buildFileName;
    
    @TestApiParameter(seq=3, 
            summary="Provide the name of the Target. Leave blank if you want default target to be picked",
            remarks="",
            mandatory=false,
            parameterGroup="inputs")
    public String targetName;

    @TestApiParameter(seq=10, 
            summary="The name that the result will be stored under.",
            remarks="",
            mandatory=true,
            parameterGroup="result")
    public String isTaskSuccessful;

    @TestApiParameter(seq=11, 
            summary="The lifespan of the result.",
            remarks="",
            mandatory=true,
            parameterGroup="result",
            defaultValue="Test")
    public ValueScope resultScope;

    /** 
     * Used to write to the test execution log.
     */
    @TestLogger
    public Logger testLogger;
    
    /** 
     * Provides access to facilities, mainly to set and get variable values.
     */
    @TestExecutionContext
    public ITestExecutionContext testExecutionContext;

  // Store the result (if appropriate).
  final String[] dummyResult = new String[1];

    @TestApiExecutor
    public void execute() {
        
        File buildFile = new File(path, buildFileName);
 
        // Prepare Ant project
        Project project = new Project();
        project.setUserProperty("ant.file", buildFile.getAbsolutePath());
        
        DefaultLogger consoleLogger = new DefaultLogger();
        consoleLogger.setErrorPrintStream(System.err);
        consoleLogger.setOutputPrintStream(System.out);
        consoleLogger.setMessageOutputLevel(Project.MSG_INFO);
        project.addBuildListener(consoleLogger);
        project.addBuildListener(new BuildListener() {
      
      @Override
      public void taskStarted(BuildEvent arg0) {
        // TODO Auto-generated method stub
        testLogger.info("=====Task Started=====");
      }
      
      @Override
      public void taskFinished(BuildEvent arg0) {
        // TODO Auto-generated method stub
        testLogger.info("=====Task Finished : " + arg0.getMessage() + "=====");
      }
      
      
      @Override
      public void buildStarted(BuildEvent arg0) {
        // TODO Auto-generated method stub
        testLogger.info("======Build started : " + arg0.getMessage() + "=====");
      }
      
      @Override
      public void buildFinished(BuildEvent arg0) {
        // TODO Auto-generated method stub
        
        if(arg0.getException() == null ) {
          testLogger.info("=====Build Successful=====");
          dummyResult[0] = "true";
        } else {
          testLogger.info("=====Build Failed=====");
          dummyResult[0] = "false";
        }
      }

      @Override
      public void messageLogged(BuildEvent arg0) {
        // TODO Auto-generated method stub
        testLogger.info(arg0.getMessage());
      }

      @Override
      public void targetFinished(BuildEvent arg0) {
        // TODO Auto-generated method stub
        testLogger.info("=====Target finished : " + arg0.getMessage() + "=====");
      }

      @Override
      public void targetStarted(BuildEvent arg0) {
        // TODO Auto-generated method stub
        testLogger.info("=====Target started : " + arg0.getMessage() + "=====");
      }
    });
 
        // Capture event for Ant script build start / stop / failure
        try {
            project.fireBuildStarted();
            project.init();
            ProjectHelper projectHelper = ProjectHelper.getProjectHelper();
            project.addReference("ant.projectHelper", projectHelper);
            projectHelper.parse(project, buildFile);
             
            // If no target specified then default target will be executed.
            if(targetName != null) {
                project.executeTarget(targetName);
            }
            else {
                project.executeTarget(project.getDefaultTarget());
            }
            project.fireBuildFinished(null);
            // testLogger.info(dummyResult[0]);
        } catch (BuildException buildException) {
            project.fireBuildFinished(buildException);
            throw new RuntimeException("!!! Unable to restart !!!", buildException);
        } finally {
            testExecutionContext.setValue(isTaskSuccessful, dummyResult[0], resultScope);
        }
        
        
        
    }
    
}