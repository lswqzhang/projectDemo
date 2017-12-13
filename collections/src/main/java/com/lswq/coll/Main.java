package com.lswq.coll;

import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // union(),intersection();disjunction(); subtract();

        List<String> a = new ArrayList<>();
        a.add("a");
        a.add("b");
        a.add("c");
        a.add("d");
        List<String> b = new ArrayList<>();
        b.add("c");
        b.add("d");
        b.add("e");
        b.add("f");
        b.add("g");

        //交集   
        Collection<String> intersection = CollectionUtils.intersection(a, b);
        System.err.println(intersection);
        //并集
        Collection<String> union = CollectionUtils.union(a, b);
        System.err.println(union);
        //交集的补集   
        Collection<String> disjunction = CollectionUtils.disjunction(a, b);
        System.err.println(disjunction);
        //集合相减   
        Collection<String> subtractA = CollectionUtils.subtract(a, b);
        System.err.println(subtractA);
        Collection<String> subtractB = CollectionUtils.subtract(b, a);
        System.err.println(subtractB);

    }
}
