import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.demosoft.testgame.AppConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by Andrii_Korkoshko on 5/19/2016.
 */
public class Launcher {

    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfiguration.class);
      /*  LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "graphicsdemo";
        //cfg.useGL20 = true;
        cfg.width = 480;
        cfg.height = 320;

        new LwjglApplication(new LibGDXApplication(), cfg);*/
    }
}
