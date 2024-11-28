package database;

public interface SchemaDB {
    String USER ="root";
    String PASSWORD ="";

    String DB_NAME = "ae2concesionariopasajeros";

    String TAB_CH = "coches";
    String COL_CH_ID = "id";
    String COL_CH_MARCA = "marca";
    String COL_CH_MODELO = "modelo";
    String COL_CH_CV = "cv";
    String COL_CH_PRE = "precio";

    String TAB_PA="pasajeros";
    String COL_PA_ID="id";
    String COL_PA_NAME = "nombre";
    String COL_PA_EDAD = "edad";
    String COL_PA_PESO = "peso";
    String COL_PA_ID_CO="idcoche";
}
