import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses({ HardConstraintTest.class, SoftConstraintTests.class, TreeTests.class })
public class AllTests {

}
