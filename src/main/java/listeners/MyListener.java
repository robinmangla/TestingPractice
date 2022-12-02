package listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import org.apache.logging.log4j.LogManager;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import utils.DateTimeUtilities;

import java.io.File;
import java.io.PrintStream;
import java.util.logging.Logger;

public class MyListener implements ITestListener {
    public static ExtentSparkReporter sparkReporter;
    public static ExtentReports extentReports;
    public static ExtentTest extentTest;
//    private Logger logger = LogManager.getLogger(MyListener.class);
//    private PrintStream logStream = IoBuilder.forLogger(logger).buildPrintStream();
//    private String reportLink;
//    public static ListMultimap<String,String> testCaseName_StatusMap = ArrayListMultimap.create();
//    public static ListMultimap<String, String> testDescriptionMap = ArrayListMultimap.create();
//    public static String testType;
//    public static String remoteDirectoryPath= ExtentManager.relativePathToReport;
//    public static String srcFolderPath = ExtentManager.absolutePathToReport;


    public void onStart(ITestContext iTestContext) {
        File f = new File(System.getProperty("user.dir") + "/Reports/AutomationReport" + DateTimeUtilities.currentSystemDate("_dd-MM-YYYY-HH-mm-ss") + ".html");
        System.out.println("Path is: " + f);
//        sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/Reports/AutomationReport" + DateTimeUtilities.currentSystemDate("_dd-MM-YYYY-HH-mm-ss") + ".html");
        sparkReporter = new ExtentSparkReporter(f);
        //    System.out.println("path is: " + System.getProperty("user.dir") + "/Reports/AutomationReport" + DateTimeUtilities.currentSystemDate("_dd-MM-YYYY-HH-mm-ss") + ".html");
        sparkReporter.config().setDocumentTitle("Test Execution Summary Report");
        sparkReporter.config().setReportName("Test Execution Summary Report");
        sparkReporter.config().setTheme(Theme.STANDARD);

        extentReports = new ExtentReports();
        extentReports.attachReporter(sparkReporter);
        extentReports.setSystemInfo("HostName", "LocalHost");
        extentReports.setSystemInfo("Automation Flow", "Automation Practice");
        extentReports.setSystemInfo("Tester Name", "Robin");

        System.out.println("Execution of test cases has started");
    }
    public void onTestStart(ITestResult result) {
        extentTest = extentReports.createTest(result.getMethod().getMethodName());
//        setUpRestAssured();
        System.out.println("Test Started: " + result.getMethod().getMethodName());
    }
//    private void setUpRestAssured() {
//        RestAssured.requestSpecification = new RequestSpecBuilder()
////                .setContentType(ContentType.JSON)
////                .setBaseUri(configuration.env())
//                .addFilter(RequestLoggingFilter.logRequestTo(logStream))
//                .addFilter(ResponseLoggingFilter.logResponseTo(logStream))
//                .build();
//        RestAssured.responseSpecification = new ResponseSpecBuilder()
//                .build();
//        RestAssured.config = RestAssuredConfig.config()
//                .objectMapperConfig(new ObjectMapperConfig().jackson2ObjectMapperFactory((cls, charset) -> ObjectMapperConfigurator.getObjectMapper()));
//    }

    public void onTestSuccess(ITestResult result) {
        extentTest.log(Status.PASS, "Test Case Passed is: " + result.getMethod().getMethodName());
        extentReports.flush();
        System.out.println("Test Passed: " + result.getMethod().getMethodName());
    }

    public void onTestFailure(ITestResult result) {
        extentTest.log(Status.FAIL, "Test Case Failed is: " + result.getMethod().getMethodName());
        extentTest.log(Status.FAIL, "Test Case Failed and Exception is: " + result.getThrowable());
        extentReports.flush();
        System.out.println("Test Failed: " + result.getMethod().getMethodName());
    }

    public void onTestSkipped(ITestResult result) {
        extentTest.log(Status.SKIP, "Test Case Skipped is: " + result.getMethod().getMethodName());
        extentReports.flush();
        System.out.println("Test Skipped: " + result.getMethod().getMethodName());
    }

    public void onFinish(ITestContext iTestContext) {

        //Collect the group names to add them in the email title
/*        String executeGroup = testContext.getSuite().getName();
        if(testContext.getIncludedGroups().length > 0){
            executeGroup = Stream.of(testContext.getIncludedGroups()).collect(Collectors.joining());
        }
        String emailRecipient = System.getProperty("user.name")+"@rci.rogers.com";
        String propEmailRecipient = System.getProperty("EmailId");
        System.out.println("value of email id is: " + propEmailRecipient);
        if(!(propEmailRecipient==null || propEmailRecipient.isEmpty())){
            emailRecipient = System.getProperty("user.name")+"@rci.rogers.com"+"," + propEmailRecipient;
        }
        String strReportPath;
        String baseLink;
        if(System.getenv("NETSTORAGE_ACCESS_KEY")!=null) {
            strReportPath = FileUpload.UploadToNetStorage();
            baseLink = "https://qa1.rogers.com/Digital-QE/report/";
        }else{
            strReportPath = extentReportsUpload();
            baseLink = "http://10.18.97.209/";
        }
        reportLink = baseLink + strReportPath;
        //The if block will get executed if the test run is triggered from local machine or any machine
        //where BUILD_TIMESTAMP is not setup. BUILD_TIMESTAMP env variable is set by Jenkins job.
        if((System.getenv("BUILD_TIMESTAMP")==null) || System.getenv("BUILD_TIMESTAMP").equals("")){
            //Upload test reports to server and send email
            SendEmail.sendEmail(executeGroup, emailRecipient, reportLink, testCaseName_StatusMap,testDescriptionMap, testType);
        }else if(!(propEmailRecipient==null || propEmailRecipient.isEmpty())) {
            //Don't send email to system user when run tests on Jenkins
            SendEmail.sendEmail(executeGroup, propEmailRecipient,reportLink, testCaseName_StatusMap,testDescriptionMap,testType);
        }*/
        System.out.println("Execution of Test Cases are finished");
    }
/*    catch (IOException | MessagingException e) {
        e.printStackTrace();
    }*/

/*    public static String extentReportsUpload() {

        FTPClient client = new FTPClient();
        FileInputStream fis = null;
        String strFileName="";

        try {
            client.connect(System.getenv("FTP_SERVER"));
            client.login(System.getenv("FTP_USERNAME"), System.getenv("FTP_PASSWORD"));
            // Change the format and folder as per your project needs
            client.setFileType(FTPClient.BINARY_FILE_TYPE);

//            File folder = new File(srcFolderPath);
            File folder = new File(System.getProperty("user.dir") + "/Reports");
            if (folder.isDirectory()) {
//                client.makeDirectory(remoteDirectoryPath);
                client.makeDirectory("Reports");
//                client.changeWorkingDirectory(remoteDirectoryPath);
                client.changeWorkingDirectory("Reports");
                for (File file : folder.listFiles()) {
//                    fis = new FileInputStream(srcFolderPath+"/"+ file.getName());
                    fis = new FileInputStream(System.getProperty("user.dir") + "/Reports"+"/"+ file.getName());
                    if(file.getName().contains(".html"))
                    {
                        strFileName = file.getName();
                    }
                    client.storeFile(file.getName(), fis);
                }
                client.changeToParentDirectory();
            }
            else {
                InputStream fisf = folder.toURI().toURL().openStream();
                client.storeFile(folder.getName(), fisf);
            }
            client.logout();

            //SendEmail.sendEmail(remoteDirectoryPath+strFileName);

        } catch (IOException e) {
            e.printStackTrace();
            e.getCause();
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
                client.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ("Reports"+strFileName);
    }*/

}
