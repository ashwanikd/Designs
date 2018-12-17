import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.*;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.FPSAnimator;

import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;

public class Epispiral{

    //################################################################################################################//

    //################################################################################################################//

    GLProfile profile;
    GLCapabilities capabilities;
    GLCanvas canvas;
    Frame frame;
    //################################################################################################################//

    //################################################################################################################//

    Epispiral(){
        profile = GLProfile.get(GLProfile.GL2);
        capabilities = new GLCapabilities(profile);
        canvas = new GLCanvas(capabilities);

        EventListener eventListener = new EventListener();

        canvas.addGLEventListener(eventListener);


        frame = new Frame("Epispiral");

        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(d);

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
        LinkedList<Point> l = new LinkedList<Point>();
        //double r = 100;
        double theta = 0;
        double interval = 0.005;
        double b = 30;
        double n = 10;
        public void init(GLAutoDrawable glAutoDrawable) {
            for(int i=0;i<30000;i++){
                addPoint();
            }
        }

        public void dispose(GLAutoDrawable glAutoDrawable) {

        }

        public void display(GLAutoDrawable drawable) {
            final GL2 gl = drawable.getGL().getGL2();
            //gl.glScaled(0.5,1,1);
            gl.glColor3d(1,1,1);
            //gl.glPointSize(3f);
            gl.glBegin( GL2.GL_POINTS);
            for(int i=0;i<l.size()-1;i++){
                gl.glVertex3d(l.get(i).x/2,l.get(i).y,l.get(i).z);
                //gl.glVertex3d(l.get(i+1).x/2,l.get(i+1).y,l.get(i+1).z);
            }
            /*if(l.size()>0) {
                gl.glVertex3d(l.get(l.size() - 1).x / 2, l.get(l.size() - 1).y, l.get(l.size() - 1).z);
                gl.glVertex3d(l.get(l.size() - 2).x / 2, l.get(l.size() - 2).y, l.get(l.size() - 2).z);
            }*/
            gl.glEnd();
            addPoint();
            gl.glFlush();
        }

        boolean addPoint(){
            theta = theta + interval;
            l.add(new Point(b*(1/Math.cos(n*theta))*Math.cos(theta)/100,b*(1/Math.cos(n*theta))*Math.sin(theta)/100,0));
            //l.add(new Point(-1*r*Math.pow(theta,-1/2)*Math.cos(theta)/100,-1*r*Math.pow(theta,-1/2)*Math.sin(theta)/100,0));
            return true;
        }

        public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {

        }
    }
}
