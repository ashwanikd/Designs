import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.*;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.FPSAnimator;

import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;

public class waveEquation1{

    //################################################################################################################//

    //################################################################################################################//

    double pos[];
    GLProfile profile;
    GLCapabilities capabilities;
    GLCanvas canvas;
    Frame frame;
    //################################################################################################################//

    //################################################################################################################//

    waveEquation1(double amp,double freq, double n){
        profile = GLProfile.get(GLProfile.GL2);
        capabilities = new GLCapabilities(profile);
        canvas = new GLCanvas(capabilities);

        EventListener eventListener = new EventListener();

        eventListener.initialize(amp,freq,n);

        canvas.addGLEventListener(eventListener);


        frame = new Frame("Archimedian Spiral");

        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(500,500);

        canvas.setSize(frame.getSize());
        frame.add(canvas);

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }
        });

        frame.setVisible(true);

        FPSAnimator animator = new FPSAnimator(canvas,300,true);
        animator.start();

    }

    //################################################################################################################//

    //################################################################################################################//

    class EventListener implements GLEventListener{
        double t = 0.0;
        int NUMBER_OF_POINTS=5000;
        double amp,freq;
        double pos[];
        double x[];
        double K, omega;
        int alpha = 100;
        void initialize(double amp,double freq, double n) {
            this.amp = amp;
            this.freq = freq;
            pos = new double[NUMBER_OF_POINTS];
            K = n * Math.PI ;
            omega = 2 * Math.PI * freq;
            x = new double[NUMBER_OF_POINTS];
            for(int i=0;i<(NUMBER_OF_POINTS/2);i++){
                x[i] = -1.0 *(double)i/(double)(NUMBER_OF_POINTS/2);
            }
            for(int i=0;i<(NUMBER_OF_POINTS/2);i++){
                x[i+(NUMBER_OF_POINTS/2)] = (double)i/(double)(NUMBER_OF_POINTS/2);
            }
        }

        public void init(GLAutoDrawable glAutoDrawable) {
        }

        public void dispose(GLAutoDrawable glAutoDrawable) {

        }
        public void display(GLAutoDrawable drawable) {
            final GL2 gl = drawable.getGL().getGL2();
            gl.glClear(gl.GL_DEPTH_BUFFER_BIT|gl.GL_COLOR_BUFFER_BIT);
            //gl.glPointSize(10);
            gl.glLoadIdentity();
            //gl.glScaled(0.5,1,1);
            gl.glColor3d(1,1,1);
            //gl.glPointSize(3f);
            gl.glBegin( GL2.GL_POINTS);

            for(int i=0;i<NUMBER_OF_POINTS;i++){
                gl.glVertex3d(x[i],pos[i],0);
            }

            gl.glEnd();
            iteratepoints();
            gl.glFlush();
        }

        void iteratepoints(){
            t+=0.001;
            for(int i=0;i<NUMBER_OF_POINTS;i++){
                pos[i] = 0;
                for(int j=0;j<alpha;j++)
                    pos[i] = pos[i] + 2* amp * Math.sin((double)j * Math.PI * x[i]) * (Math.cos((double)j * Math.PI* freq * t)+Math.sin((double)j * Math.PI * freq * t));
                //System.out.print(pos[i]);
            }
            //System.out.println(t);
        }

        public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {

        }
    }
}
