public class Revista extends Material {
    private int edicion;
    private String tematica;

    public Revista(String codigo, String titulo, int anio, boolean disponible, int edicion, String tematica) {
        super(codigo, titulo, anio, disponible);
        this.edicion = edicion;
        this.tematica = tematica;
    }

    @Override
    public String getTipo() {
        return "Revista";
    }
}
