# Approval based testing with ApprovalTests

Here is the example of approval-based tests from [ApprovalTests.java User Guide](https://github.com/approvals/ApprovalTests.Java/blob/master/README.md)

```java
import org.approvaltests.Approvals;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class SampleArrayTest
{
  @Test
  public void testList()
  {
    String[] names = {"Llewellyn", "James", "Dan", "Jason", "Katrina"};
    Arrays.sort(names);
    Approvals.verifyAll("", names);
  }
}
```
This will a File `SampleArrayTest.testList.received.txt`

Simply rename this to `SampleTest.testList.approved.txt` and the test will now pass.
