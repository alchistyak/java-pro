public class Animal {
    String name;
    int age;

    public Animal() {};

    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void sayName() {
        System.out.println("My name is " + name);
    }

    public void sayAge() {
        System.out.println("My age is " + age);
    }
}
