package com.jiangxb.datastructure.hashtab;

import java.util.Scanner;

/**
 * @author jiangxiangbo
 * @date 2020/7/31
 * @Description: 哈希表(散列) - Google上级题
 *
 * 需求: 有个公司，当有新的员工来报道时，要求将该员工的信息加入(id,性别,年龄,住址...)，当输入员工id时，要求查到该员工的所有信息
 *
 * 要求:
 * 1) 不使用数据库，尽量节省内存速度越快越好 ===> 哈希表(散列)
 * 2) 添加时，保证按照id从低到高插入
 * 3) 使用链表来实现哈希表，该链表不带表头（第一个节点就存放雇员信息）
 *
 */
public class HashTabDemo {

    public static void main(String[] args) {

        // 创建哈希表
        HashTab hashtab = new HashTab(2);

        // 菜单
        String key = "";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("***************");
            System.out.println("add: 添加雇员");
            System.out.println("list: 显示雇员");
            System.out.println("find: 查找雇员");
            System.out.println("exit: 退出程序");
            System.out.println("***************");

            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("请输入id：");
                    int id = scanner.nextInt();
                    System.out.println("请输入名字：");
                    String name = scanner.next();
                    // 添加雇员
                    hashtab.add(new Emp(id, name));
                    break;
                case "list":
                    hashtab.list();
                    break;
                case "find":
                    System.out.println("请输入雇员id");
                    id = scanner.nextInt();
                    hashtab.findEmpById(id);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                default:
                    break;
            }
        }
    }

}

/**
 * 雇员信息
 */
class Emp {
    private int id;
    private String name;
    private Emp next;
    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Emp getNext() {
        return next;
    }

    public void setNext(Emp next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", next=" + next +
                '}';
    }
}

/**
 * 创建HashTab管理多条链表
 */
class HashTab {

    // 存储链表
    private  EmpLinkedList[] empLinkedLists;
    // 有多少条链表 empLinkedLists 长度
    private int size;

    public HashTab(int size) {
        this.size = size;
        // 初始化 empLinkedLists
        empLinkedLists = new EmpLinkedList[size];
        for (int i = 0; i < size; i++) {
            empLinkedLists[i] = new EmpLinkedList();
        }
    }

    // 散列函数，使用简单取模法
    public int hashFun(int id) {
        return id % size;
    }

    public void add(Emp  emp) {
        // 根据员工id，通过一个算法得出应该添加到哪条链表
        int empLinkedListNo = hashFun(emp.getId());
        empLinkedLists[empLinkedListNo].add(emp);
        System.out.println("添加成功");
    }

    // 遍历hashtab所有链表
    public  void list() {
        for (int i = 0; i < size; i++) {
            // list(i) 查第i+1条链表
            empLinkedLists[i].list(i);
        }
    }

    // 根据输入的id，查找雇员
    public void findEmpById(int id) {
        // 根据散列函数确定在哪条链表
        int empLinkedListNo = hashFun(id);
        Emp emp = empLinkedLists[empLinkedListNo].findEmpById(id);
        if (emp == null) {
            System.out.println("在哈希表中未找到id为" + id + "的雇员");
        } else {
            System.out.println("在第"+(empLinkedListNo+1)+"条链表中找到==>"+emp.toString());
        }
    }


}

/**
 * 创建EmpLinkedList链表
 */
class EmpLinkedList {

    // 头指针
    private Emp head;

    // 假设id自增，直接将雇员添加到链表末尾
    public void add(Emp emp) {
        if (head == null) {
            this.head = emp;
            return;
        }
        Emp curEmp = head;
        while (curEmp.getNext() != null) {
            curEmp = curEmp.getNext();
        }
        curEmp.setNext(emp);
    }

    // 遍历链表的雇员信息
    public void list(int no) {
        if (head == null) {
            // System.out.println("第"+(no+1)+"条链表为空");
            return;
        }
        System.out.println("第"+(no+1)+"条链表的信息为");
        Emp curEmp = head;
        while (curEmp != null) {
            System.out.println(curEmp.toString());
            curEmp = curEmp.getNext();
        }
    }

    // 根据id在当前链表中查找雇员
    public Emp findEmpById(int id) {
        Emp curEmp = head;
        while (curEmp != null) {
            if (curEmp.getId() == id) {
                return curEmp;
            }
            curEmp = curEmp.getNext();
        }
        return null;
    }
}
