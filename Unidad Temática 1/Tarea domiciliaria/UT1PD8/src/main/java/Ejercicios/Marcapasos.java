package Ejercicios;

public class Marcapasos {
//  • Frecuencia cardíaca (0-226)
//  • Nivel de azúcar en sangre. (0 - 1000).
//  • Máxima fuerza a la que fue expuesto (0-3.000.000.000).
//  • Mínimo tiempo entre latidos (0-100 con decimales).
//  • Batería restante. (% de batería restante con decimales con la mayor precisión posible).
//  • Código del Fabricante (Números y letras, máximo 8 caracteres).
    
    short presSanguinea; // 2 bytes
    short frecCardiaca; // 2 bytes
    short nivelAzucar; // 2 bytes                              
    long maxFuerzaExpuesto; // 8 bytes
    byte minTiempoLatidos; // 1 byte
    double bateriaRestante; // 8 bytes
    String codigoFabricante; // 2 bytes. NO ENCONTRÉ OTRO TIPO PRIMITIVO QUE ME SIRVA
   
    // Total espacio por objeto: 25 bytes
}
