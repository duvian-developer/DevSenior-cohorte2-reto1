import java.util.Arrays;
import java.util.Random;

import javax.swing.JOptionPane;

public class SimuladorViajeInterplanetario {
    public static String[] planetas = { "MARTE", "JUPITER", "SATURNO" };
    public static double[] distancias = { 225.0, 778.0, 1429.0 };
    public static double velocidad = 300_000;
    public static double consumoCombustible = 0.5; // litros por km
    public static double consumoOxigeno = 0.2; // litros por hora

    public static double distanciaKm;
    public static double tiempoViaje;
    public static double conbustibleNecesario;
    public static double oxigenoNecesario;

    public static void main(String[] args) {
        menuPlanetas(selecionarPlaneta());

    }

    /**
     * Pide al usuario que seleccione un planeta del sistema solar exterior y lo
     * devuelve como String.
     * 
     * @return El planeta seleccionado por el usuario.
     */
    public static String selecionarPlaneta() {
        String planeta = JOptionPane.showInputDialog("Seleccione un planeta\n " +
                "- " + planetas[0] + "\n" + " - " + planetas[1] + "\n" + " - " + planetas[2]).trim().toUpperCase();
        return planeta;
    }

    /**
     * Muestra un menu para el planeta seleccionado y según la opción
     * seleccionada muestra un mensaje de confirmación.
     * 
     * @param planeta El planeta seleccionado.
     */
    public static void menuPlanetas(String planeta) {
        switch (planeta) {
            case "MARTE":
                calcularDistanciaYTiempoViaje(Arrays.asList(planetas).indexOf(planeta));
                iniciarViaje();                
                break;
            case "JUPITER":
                calcularDistanciaYTiempoViaje(Arrays.asList(planetas).indexOf(planeta));
                iniciarViaje();
                break;
            case "SATURNO":
                calcularDistanciaYTiempoViaje(Arrays.asList(planetas).indexOf(planeta));
                iniciarViaje();
                break;
            default:
                int respuesta = JOptionPane.showConfirmDialog(null,
                        "Opción incorrecta. ¿Desea seleccionar un planeta?",
                        "Confirmación", JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                // Evaluar la respuesta
                if (respuesta == JOptionPane.YES_OPTION) {
                    menuPlanetas(selecionarPlaneta());
                } else if (respuesta == JOptionPane.NO_OPTION || respuesta == JOptionPane.CANCEL_OPTION) {
                    break;
                }
        }

    }

    /**
     * Calcula la distancia y el tiempo de viaje a un planeta seleccionado, y
     * muestra un mensaje de confirmación con los resultados.
     * 
     * @param indice El índice del planeta seleccionado en el array de planetas.
     */
    public static void calcularDistanciaYTiempoViaje(int indice) {
        distanciaKm = distancias[indice] * 1_000_000;
        tiempoViaje = distanciaKm / velocidad;
        conbustibleNecesario = distanciaKm / consumoCombustible;
        oxigenoNecesario = tiempoViaje * consumoOxigeno;
        String planetaSeleccionado = planetas[indice];

        JOptionPane.showMessageDialog(null, "Planeta seleccionado: " + planetaSeleccionado + "\n" +
                "Distancia a recorrer: " + String.format("%,d", (int) distanciaKm) + " km\n" +
                "Tiempo estimado de viaje: " + tiempoViaje + " horas\n" +
                "Combustible necesario: " + String.format("%,d", (int) conbustibleNecesario) + " litros\n" +
                "Oxigeno necesario: " + oxigenoNecesario + " litros");

    }

    /**
     * Solicita al usuario la confirmación para iniciar el viaje. Si el usuario
     * acepta,
     * se procede a simular un evento relacionado con el viaje. Si el usuario
     * cancela o
     * selecciona "no", se muestra un mensaje indicando que el viaje ha sido
     * cancelado.
     */
    public static void iniciarViaje() {
        int respuesta = JOptionPane.showConfirmDialog(null,
                "Desea iniciar el viaje?",
                "Confirmación", JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        // Evaluar la respuesta
        if (respuesta == JOptionPane.YES_OPTION) {
            simularEvento();
        } else if (respuesta == JOptionPane.NO_OPTION || respuesta == JOptionPane.CANCEL_OPTION) {
            JOptionPane.showMessageDialog(null, "Viaje cancelado");
        }
    }

    /**
     * Simula un evento en el viaje. El evento puede ser una falla en el sistema,
     * un asteroide en la ruta o un desvio de ruta. El usuario puede cancelar el
     * viaje
     * en cualquier momento. Al final del viaje se muestra un mensaje con los
     * resultados
     * finales.
     */
    public static void simularEvento() {
        Random random = new Random();
        double distanciaRecorrida = 0.0;
        double combustibleRestante = 0.0;
        double consumoOxigenoPorKilometro = 0.01; // litros/kilómetro
        for (int i = 0; i < 100; i++) {
            distanciaRecorrida = distanciaKm * (i / 100.0);
            combustibleRestante = conbustibleNecesario - distanciaRecorrida / consumoCombustible;
            oxigenoNecesario -= consumoOxigenoPorKilometro;
            if (random.nextInt(100) < 15) { // falla en el sistema
                JOptionPane.showMessageDialog(null, "Evento: Falla en el sistema\n" +
                        "Combustible restante: " + String.format("%,d", (int) combustibleRestante) + " litros\n" +
                        "Oxigeno restante: " + String.format("%.2f", oxigenoNecesario) + " litros" + "\n" +
                        "Distancia recorrida: " + String.format("%,d", (int) distanciaRecorrida) + " km");
            } else if (random.nextInt(100) < 25) { // asteroide en la ruta
                JOptionPane.showMessageDialog(null, "Evento: Asteroide en la ruta\n" +
                        "Combustible restante: " + String.format("%,d", (int) combustibleRestante) + " litros\n" +
                        "Oxigeno restante: " + String.format("%.2f", oxigenoNecesario) + " litros" + "\n" +
                        "Distancia recorrida: " + String.format("%,d", (int) distanciaRecorrida) + " km");
            } else if (random.nextInt(100) < 30) { // desvio de ruta
                JOptionPane.showMessageDialog(null, "Evento: Desvio de ruta\n" +
                        "Combustible restante: " + String.format("%,d", (int) combustibleRestante) + " litros\n" +
                        "Oxigeno restante: " + String.format("%.2f", oxigenoNecesario) + " litros" + "\n" +
                        "Distancia recorrida: " + String.format("%,d", (int) distanciaRecorrida) + " km");
            }
        }

        JOptionPane.showMessageDialog(null, "Evento: Final del viaje\n" +
                "Combustible restante: " + String.format("%,d", (int) combustibleRestante) + " litros\n" +               
                "Oxigeno restante: " + String.format("%.2f", oxigenoNecesario));

    }

}
