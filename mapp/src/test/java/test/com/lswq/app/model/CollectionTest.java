package test.com.lswq.app.model;

import com.lswq.app.model.Master;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Created by zhangsw on 2016/12/30.
 */
public class CollectionTest {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(CollectionTest.class);
    
    private Master master;
    
    @Before
    public void setUp() {
        master = new Master(123, "zhang");
    }
    
    @Test
    public void masterReflect() throws IllegalAccessException, InstantiationException, NoSuchFieldException {
       
        Field field = Master.class.getDeclaredField("name");
        
        field.setAccessible(true);

        String fieldValue = (String) field.get(master);
        

        System.err.println(fieldValue);

    }
    
    @Test
    public void iteratorListRemove() {
        List<String> list = new ArrayList();
        list.add("one");
        list.add("two");
        list.add("two");
        list.add("two");
        list.add("two");

        Iterator<String> iterator = list.iterator();
        
        while (iterator.hasNext()) {
            String next = iterator.next();
            
            if ("one".equals(next)) {
                iterator.remove();
            }
        }
        
        
        
        System.err.println("list：" + list);
    }


    @Test
    public void iteratorMapRemove() {
        Map<String, String> map = new HashMap<>();
       
        map.put("aaa", "aaa");
        map.put("bbb", "bbb");
        map.put("ccc", "ccc");
        map.put("ddd", "ddd");

        Set<Map.Entry<String, String>> entries = map.entrySet();

        Iterator<Map.Entry<String, String>> iterator = entries.iterator();

        while (iterator.hasNext()) {
            Map.Entry<String, String> next = iterator.next();

            if ("aaa".equals(next.getKey())) {
                iterator.remove();
            }
        }

       
        System.err.println("map：" + map);
    }
    
    
    @Test
    public void comparableTest() {
        Master m1 = new Master(1,"aaa");
        Master m2 = new Master(3,"bbb");
        Master m3 = new Master(2,"ccc");
        Master m4 = new Master(5,"ddd");
        Master m5 = new Master(4,"eee");
        
        List<Master> list = new ArrayList<>();
        list.add(m2);
        list.add(m1);
        list.add(m4);
        list.add(m5);
        list.add(m3);
        
        LOGGER.info("list original sort\t\t：{}", list);
        
        Collections.sort(list);
        
        LOGGER.info("list sort by default \t\t：{}", list);
        
        Collections.sort(list, Comparator.comparing(Master::getName));


        LOGGER.info("list sort by name as asc\t：{}", list);


        Collections.sort(list, Comparator.comparing(Master::getId));
        
        LOGGER.info("list sort by id as asc\t：{}", list);
        
        
        Collections.sort(list, (o1, o2) -> o2.getName().compareTo(o1.getName()));

        LOGGER.info("list sort by name as desc\t：{}", list);
        
        
        Collections.sort(list, (o1, o2) -> o2.getId() - o1.getId());
        
        LOGGER.info("list sort by id as desc\t：{}", list);


        int i = Collections.binarySearch(list, m1);

        System.err.println("m1 = " + i);

    }
}
