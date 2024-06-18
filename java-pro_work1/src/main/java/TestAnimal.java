public class TestAnimal {
    Animal animal;

    public TestAnimal() {
        animal = new Animal("Terry", 13);
    }

    @Test(priority = 1)
    public void sayAge() {
        animal.sayAge();
    }

    @Test(priority = 3)
    public void sayName() {
        animal.sayName();
    }

    @BeforeSuite
    public static void printBefore() {
        System.out.println("-->>");
        System.out.println("Class: " + Animal.class.getName());
    }

    @AfterSuite
    public static void printAfter() {
        System.out.println("<<--");
    }

    @BeforeTest
    @CsvSource("Kris, 5")
    public void setName(String name, int age) {
        animal.setName(name);
        animal.setAge(age);
    }
}
