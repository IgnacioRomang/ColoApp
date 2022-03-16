package romang.ignacio.coloapp.model;

public class Inversion {
    private int id;
    private Long date;
    private String codename;
    private String activo;
    private Integer montoInicial;
    private Integer resultado;

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public String getCodename() {
        return codename;
    }

    public void setCodename(String codename) {
        this.codename = codename;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public Integer getMontoInicial() {
        return montoInicial;
    }

    public void setMontoInicial(Integer montoInicial) {
        this.montoInicial = montoInicial;
    }

    public Integer getResultado() {
        return resultado;
    }

    public void setResultado(Integer resultado) {
        this.resultado = resultado;
    }

    public Inversion(Long date, String activo, Integer montoInicial, Integer resultado) {
        this.date = date;
        this.activo = activo;
        this.montoInicial = montoInicial;
        this.resultado = resultado;
    }
}
