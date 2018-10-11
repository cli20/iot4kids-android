package com.adalaachref.iot4kids.model;

/**
 * Created by dell on 12/11/2017.
 */

public class Component {
    private int idComponent;
    private String name;
    private String img;

    public Component(int idComponent, java.lang.String name, java.lang.String img) {
        this.idComponent = idComponent;
        this.name = name;
        this.img = img;
    }

    public int getIdComponent() {
        return idComponent;
    }

    public void setIdComponent(int idComponent) {
        this.idComponent = idComponent;
    }

    public java.lang.String getName() {
        return name;
    }

    public void setName(java.lang.String name) {
        this.name = name;
    }

    public java.lang.String getImg() {
        return img;
    }

    public void setImg(java.lang.String img) {
        this.img = img;
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;

        Component component = (Component) object;

        if (idComponent != component.idComponent) return false;
        if (name != null ? !name.equals(component.name) : component.name != null) return false;
        return img != null ? img.equals(component.img) : component.img == null;

    }

    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + idComponent;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (img != null ? img.hashCode() : 0);
        return result;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Component{" +
                "idComponent=" + idComponent +
                ", name=" + name +
                ", img=" + img +
                '}';
    }
}
