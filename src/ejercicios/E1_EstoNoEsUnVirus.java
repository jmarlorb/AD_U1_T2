package ejercicios;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class E1_EstoNoEsUnVirus {

	public static void main(String[] args) {
		String virusRoute = System.getProperty("user.dir") + System.getProperty("file.separator")
				+ "EstoDefinitivamenteNoEsUnVirus.jar";
		System.out.println(virusRoute);
		File virus = new File(virusRoute);
		if (virus.exists()) {
			File dir = new File(System.getProperty("user.dir"));
			File virusCopy;
			String[] list = dir.list();
			int copyCounter = 10;
			int numberLastCopy = 0;
			for (String s : list) {

				if (s.indexOf("EstoDefinitivamenteNoEsUnVirus_COPIA") != -1
						&& s.indexOf("EstoDefinitivamenteNoEsUnVirus_COPIA") == s
								.lastIndexOf("EstoDefinitivamenteNoEsUnVirus_COPIA")
						&& s.indexOf('.') != -1 && s.indexOf('.') == s.lastIndexOf('.')) {
					s = s.substring(s.indexOf("EstoDefinitivamenteNoEsUnVirus_COPIA")
							+ "EstoDefinitivamenteNoEsUnVirus_COPIA".length(), s.indexOf('.'));
					try {
						if (numberLastCopy < Integer.parseInt(s)) {
							if (numberLastCopy + 1 < Integer.parseInt(s)) {
								for (int i = 1; i <= (Integer.parseInt(s) - numberLastCopy); i++) {
									if (copyCounter > 0) {
										virusCopy = new File(dir,
												"EstoDefinitivamenteNoEsUnVirus_COPIA" + (numberLastCopy + i) + ".jar");
										if (!virusCopy.exists()) {
										copy(virus, virusCopy);
										copyCounter--;}
									}
								}
							}
							numberLastCopy = Integer.parseInt(s);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}

				}
			}
			for (int i = 1; i <= copyCounter; i++) {

				virusCopy = new File(dir, "EstoDefinitivamenteNoEsUnVirus_COPIA" + (numberLastCopy + i) + ".jar");
				copy(virus, virusCopy);

			}

		}

	}

	private static void copy(File virus, File virusCopy) {
		try {
			Files.copy(virus.toPath(), virusCopy.toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
