package com.dprogramacionjg.historialtecnologico.model;

public class Piv_pere_mate {
    private String Id;
    private String Id_persona;
    private String Id_material;
    private String Fecha_prestamo;
    private String Fecha_devolucion;
    private String Estado_prestamo;
    private String Estado;

    public Piv_pere_mate() {
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getId_material() {
        return Id_material;
    }

    public void setId_material(String id_material) {
        Id_material = id_material;
    }

    public String getId_persona() {
        return Id_persona;
    }

    public void setId_persona(String id_persona) {
        Id_persona = id_persona;
    }

    public String getFecha_prestamo() {
        return Fecha_prestamo;
    }

    public void setFecha_prestamo(String fecha_prestamo) {
        Fecha_prestamo = fecha_prestamo;
    }

    public String getFecha_devolucion() {
        return Fecha_devolucion;
    }

    public void setFecha_devolucion(String fecha_devolucion) {
        Fecha_devolucion = fecha_devolucion;
    }

    public String getEstado_prestamo() {
        return Estado_prestamo;
    }

    public void setEstado_prestamo(String estado_prestamo) {
        Estado_prestamo = estado_prestamo;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }

    @Override
    public String toString() {
        return Id_persona +" "+ Id_material;
    }
}
