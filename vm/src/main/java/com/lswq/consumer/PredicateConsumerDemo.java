package com.lswq.consumer;

import java.util.function.Consumer;
import java.util.function.Predicate;

public class PredicateConsumerDemo {

    /**
     * 付费折扣
     *
     * @param student
     * @param predicate
     * @param consumer
     * @return
     */
    public static Student updateStudentFee(Student student, Predicate<Student> predicate, Consumer<Student> consumer){
        //Use the predicate to decide when to update the discount.
        if ( predicate.test(student)){
            //Use the consumer to update the discount value.
            consumer.accept(student);
        }
        return student;
    }

}
