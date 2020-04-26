
class Person {

    public String fullName, position, phoneNumber, email;
    public int salary, age;

    public Person (String fullName, String position, String phoneNumber, String email, int salary, int age){
        this.fullName = fullName;
        this.position = position;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.salary = salary;
        this.age = age;
    }

    @Override
    public String toString() {
        return fullName + ", position " + position +
                ", phone " + phoneNumber + ", email " + email +
                ", salary " + salary+ ", age " + age + ".";
    }
}

public class HW_5 {
    public static void main(String[] args) {
    Person [] persArray = new Person[5];
        persArray[0] = new Person("O.I.Ivanov", "fireman", "01", "fire@ru", 1, 21);
        persArray[1] = new Person("B.M.Sidorov", "policeman", "02", "police@ru", 2, 42);
        persArray[2] = new Person("O.A.Kozlov", "doctor", "03", "doctor@ru", 3, 64);
        persArray[3] = new Person("R.I.Petrov", "gasman", "04", "gas@ru", 4, 28);
        persArray[4] = new Person("D.T.Voland", "devil", "66", "hell@com", 666, 666);

        System.out.println("Cписок сотрудников:");
        for (int i = 0; i < persArray.length; i++) {
            System.out.println("Person" + (i+1) + " "  + persArray[i]);
        }

        System.out.println("\nСотрудники старше 40:");
        for (int i = 0; i < persArray.length ; i++) {
            if (persArray[i].age > 40)
                System.out.println(persArray[i].fullName +"(" +persArray[i].age + ")");
        }
    }
}