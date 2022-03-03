package indians;

import java.util.ArrayList;

public class Indian {
    private String name;
    private String tribe;
    private String sex;
    private int age;
    private ArrayList<String> tools;

    public Indian(String row) {
        String[] data = row.split(";");
        this.name = data[0];
        this.tribe = data[1];
        this.sex = data[2].equals("f")? "male" : "female";
        this.age = Integer.parseInt(data[3]);
        for(int i = 4; i<data.length; i++) {
            assert this.tools != null;
            this.tools.add(data[i]);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTribe() {
        return tribe;
    }

    public void setTribe(String tribe) {
        this.tribe = tribe;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public ArrayList<String> getTools() {
        return tools;
    }

    public void setTools(ArrayList<String> tools) {
        this.tools = tools;
    }

    @Override
    public String toString() {
        String s = name +" from the " + tribe+" tribe is a(n) "+age + " year old " + sex + " and has a";
        for(String item:this.tools) s+= item + " ";
        return s;
    }
}