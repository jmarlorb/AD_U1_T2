package suplementarias;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class MiObjectOutputStream extends ObjectOutputStream {
/**Constructor que recibe OutputStream */
public MiObjectOutputStream(OutputStream out) throws IOException {
super(out);
}
/**Constructor sin parametros */
protected MiObjectOutputStream() throws IOException, SecurityException {
super();
}
/**Redefinicion del metodo de escribir la cabecera para que no haga nada */
protected void writeStreamHeader() throws IOException{
}
}
