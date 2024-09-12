# Approval based testing with ApprovalTests

Instead of writing individual assertions, ApprovalTests focuses on verifying the overall output. This is particularly useful when testing complex objects, files, or outputs where writing assertions would be tedious.

Here is the example of approval-based tests from [ApprovalTests.java User Guide](https://github.com/approvals/ApprovalTests.Java/blob/master/README.md)

```java
import java.util.Arrays;
import org.approvaltests.Approvals;
import org.junit.jupiter.api.Test;

public class SampleArrayTest {

  @Test
  public void testList() {
    String[] names = { "Llewellyn", "James", "Dan", "Jason", "Katrina" };
    Arrays.sort(names);
    Approvals.verifyAll("", names);
  }
}

```

This will a File `SampleArrayTest.testList.received.txt`

Simply rename this to `SampleTest.testList.approved.txt` and the test will now pass.
