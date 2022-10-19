import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner teclado = new Scanner(System.in);
        ProcessBuilder pb = new ProcessBuilder("notepad.exe");
        Process process = null;


        System.out.println("¿Cuantos procesos quieres?");
        int numeroTeclado = teclado.nextInt();

        ArrayList<Process> cargadeProcesos = new ArrayList<>();
        long horaInicio = 0;

        for (int i = 0; i < numeroTeclado; i++) {
            process = pb.start();
            horaInicio = System.currentTimeMillis();
            cargadeProcesos.add(process);
        }

        for (int i = 0; i < cargadeProcesos.size(); i++) {
            Process misProcesos = cargadeProcesos.get(i);
            while (misProcesos.isAlive()) {
                misProcesos.waitFor(500, TimeUnit.MILLISECONDS);
                misProcesos.destroyForcibly();
            }

            long horaFinal = System.currentTimeMillis();

            System.out.println("***Proceso*** " + i + ":\nHora de inicio --> " + horaInicio +
                    "\nHora final --> "+ + horaFinal +
                    "\nDiferencia de MS " + (horaFinal-horaInicio));
        }

        try {
            int exitCode = process.waitFor();
            System.out.println("procesos terminados ");
        } catch (InterruptedException e) {
            System.out.println("Ta petao");
            System.err.println("El proceso no se ha podido ejecutar");
            System.err.println("Esto hizo Cascaso");
        }
    }
}
