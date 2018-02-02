package com.lswq.consumer;

import static com.lswq.consumer.PredicateConsumerDemo.updateStudentFee;

public class Test {

    public static void main(String[] args) {

        Student student1 = new Student("Ashok", "Kumar", 9.5);

        student1 = updateStudentFee(student1,
                //Lambda expression for Predicate interface
                student -> student.grade > 8.5,
                //Lambda expression for Consumer interface
                student -> student.feeDiscount = 30.0);

        student1.printFee();

        Student student2 = new Student("Rajat", "Verma", 8.0);

        student2 = updateStudentFee(student2,
                //Lambda expression for Predicate interface
                student -> student.grade >= 8,
                //Lambda expression for Consumer interface
                student -> student.feeDiscount = 20.0);

        student2.printFee();

    }


}
