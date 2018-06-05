package com.yammer;

import javax.validation.constraints.NotNull;

public class NodeRegister {

    @NotNull
    private Integer id;
    @NotNull
    private String IPAdress;
    private String Name;
    private String device;

    public NodeRegister(){

    }

    public NodeRegister(Integer id, String IPAdress, String Name, String device) {
        this.id = id;
        this.IPAdress = IPAdress;
        this.Name = Name;
        this.device = device;
    }

    @Override
    public String toString() {
        return "NodeRegister{" +
                "id=" + id +
                ", IPAdress='" + IPAdress + '\'' +
                ", Name='" + Name + '\'' +
                ", device='" + device + '\'' +
                '}';
    }

    public void setName(String name) {
        Name = name;
    }

    public void setIPAdress(String IPAdress) {

        this.IPAdress = IPAdress;
    }

    public void setId(Integer id) {

        this.id = id;
    }

    public void setDevice(String device) {

        this.device = device;
    }

    public String getName() {

        return Name;
    }

    public String getDevice() {

        return device;
    }

    public String getIPAdress() {

        return IPAdress;
    }

    public Integer getId() {

        return id;
    }
}
