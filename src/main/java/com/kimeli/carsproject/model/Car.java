package com.kimeli.carsproject.model;

import javax.persistence.*;

@Entity
@Table(name = "cardata")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String carreg;
    private String carmade;
    private String carmodel;
    private String capturepicturepath;
    private Integer carownerid;

    public Car() {
    }

    public Car(Long id, String carreg, String carmade, String carmodel, String capturepicturepath, Integer carownerid) {
        this.id = id;
        this.carreg = carreg;
        this.carmade = carmade;
        this.carmodel = carmodel;
        this.capturepicturepath = capturepicturepath;
        this.carownerid = carownerid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCarreg() {
        return carreg;
    }

    public void setCarreg(String carreg) {
        this.carreg = carreg;
    }

    public String getCarmade() {
        return carmade;
    }

    public void setCarmade(String carmade) {
        this.carmade = carmade;
    }

    public String getCarmodel() {
        return carmodel;
    }

    public void setCarmodel(String carmodel) {
        this.carmodel = carmodel;
    }

    public String getCapturepicturepath() {
        return capturepicturepath;
    }

    public void setCapturepicturepath(String capturepicturepath) {
        this.capturepicturepath = capturepicturepath;
    }

    public Integer getCarownerid() {
        return carownerid;
    }

    public void setCarownerid(Integer carownerid) {
        this.carownerid = carownerid;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", carreg='" + carreg + '\'' +
                ", carmade='" + carmade + '\'' +
                ", carmodel='" + carmodel + '\'' +
                ", capturepicturepath='" + capturepicturepath + '\'' +
                ", carownerid=" + carownerid +
                '}';
    }
}
