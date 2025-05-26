package TCP.Cliente.Clase;

import java.io.*;
import java.net.Socket;

public class Cliente {
    private static final int puerto = 5000;
    private static final String IP = "192.168.100.8";
    public static String enviarNombre(String nombre) throws Exception {
        Socket cliente = new Socket(IP, puerto);
        DataOutputStream dos = new DataOutputStream(cliente.getOutputStream());
        DataInputStream dis = new DataInputStream(cliente.getInputStream());
        dos.writeUTF(nombre);
        String respuesta = dis.readUTF();
        cliente.close();
        return respuesta;
    }

    public void escribir(String contenido, String ruta) throws Exception {
        File archivo = new File(ruta);
        boolean existe = archivo.exists();

        FileOutputStream fos = new FileOutputStream(archivo, true);
        ObjectOutputStream oos = existe ?
                new ObjectOutputStream(fos) {
                    protected void writeStreamHeader() throws IOException {
                        reset();
                    }
                } : new ObjectOutputStream(fos);

        oos.writeObject(contenido);
        oos.close();
    }
}
