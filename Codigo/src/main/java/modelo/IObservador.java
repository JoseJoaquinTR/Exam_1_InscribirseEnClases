package modelo;

public interface IObservador {

    void updateDisponibles(ModeloInscripcion modelo);

    void updateInscritos(ModeloInscripcion modelo);

    void updateTotal(ModeloInscripcion modelo);

    void updateFichaPago(ModeloInscripcion modelo);

    void updateMensaje(ModeloInscripcion modelo);
}
