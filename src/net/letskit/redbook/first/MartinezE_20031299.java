package net.letskit.redbook.first;
import net.letskit.redbook.glskeleton;
import com.sun.opengl.util.GLUT;
import com.sun.opengl.util.texture.Texture;
import com.sun.opengl.util.texture.TextureIO;
import net.letskit.redbook.glskeleton;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.media.opengl.*;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import javax.media.opengl.*;
import javax.media.opengl.glu.*;
import com.sun.opengl.util.*;

import javax.media.opengl.GLEventListener;

/**
 * This is a simple double buffered program. Pressing the left mouse button
 * rotates the rectangle. Pressing the middle mouse button stops the rotation.
 *
 * @author Kiet Le (Java conversion)
 */
public class MartinezE_20031299//
        extends glskeleton//
        implements //
        GLEventListener//
        , KeyListener//
        , MouseListener//
{
    private float spin = 0f, spinDelta = 0f;
     private GLU glu;
    private GLUT glut;
    private Texture texture;
    
    public MartinezE_20031299() {
    }
    
    public static void main(String[] args) {
        //
         GLCapabilities caps = new GLCapabilities();
        caps.setDoubleBuffered(true);

        GLJPanel canvas = new GLJPanel(caps);
        MartinezE_20031299 demo = new MartinezE_20031299();
        demo.setCanvas(canvas);
        canvas.addGLEventListener(demo);
        demo.setDefaultListeners(demo);
        demo.setDefaultAnimator(24);

        JFrame frame = new JFrame("Octaedro");
        frame.setSize(512, 512);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.getContentPane().add(canvas);
        frame.setVisible(true);
        canvas.requestFocusInWindow();
        demo.animate();
    }
    
    public void init(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();

        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        gl.glShadeModel(GL.GL_FLAT);
        
        // Habilita la prueba de profundidad (z-buffering)
        gl.glEnable(GL.GL_DEPTH_TEST);
        
        
        glu = new GLU();
        glut = new GLUT();
      
        try {
            // Cargar la textura
            String imagePath = MartinezE_20031299.class.getResource("/armaroctaedro.jpg").getPath();
            texture = TextureIO.newTexture(new File(imagePath), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public synchronized void display(GLAutoDrawable drawable) {
       GL gl = drawable.getGL();

        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();
        gl.glTranslatef(0.0f, 0.0f, -5.0f);
        gl.glRotatef(spin, 1.0f, 1.0f, 1.0f);
        texture.enable();
        texture.bind();

        // Dibujar un octaedro
        float s = 1.0f;
        float h = (float) Math.sqrt(2.0) / 2;
        
        gl.glBegin(GL.GL_TRIANGLES);

        // Cara superior
        gl.glTexCoord2f(0.2508f, 0.5014f); // Rosa pastel
        gl.glVertex3f(0.0f, s, 0.0f);
        gl.glTexCoord2f(0.7278f, 0.5014f);
        gl.glVertex3f(-s, 0.0f, 0.0f);
        gl.glTexCoord2f(0.4885f, 0.2576f);
        gl.glVertex3f(0.0f, 0.0f, -s);
        
        gl.glTexCoord2f(0.0114f, 0.2576f); // Verde
        gl.glVertex3f(0.0f, s, 0.0f);
        gl.glTexCoord2f(0.2508f, 0.5014f);
        gl.glVertex3f(0.0f, 0.0f, -s);
        gl.glTexCoord2f(0.4885f, 0.2576f);
        gl.glVertex3f(s, 0.0f, 0.0f);

        gl.glTexCoord2f(0.2508f, 0.5014f); // Gris
        gl.glVertex3f(0.0f, s, 0.0f);
        gl.glTexCoord2f(0.7278f, 0.5014f);
        gl.glVertex3f(s, 0.0f, 0.0f);
        gl.glTexCoord2f(0.4868f, 0.7472f);
        gl.glVertex3f(0.0f, 0.0f, s);

        gl.glTexCoord2f(0.9639f, 0.2576f); // Amarillo
        gl.glVertex3f(0.0f, s, 0.0f);
        gl.glTexCoord2f(0.4885f, 0.2576f);
        gl.glVertex3f(0.0f, 0.0f, s);
        gl.glTexCoord2f(0.7278f, 0.5014f);
        gl.glVertex3f(-s, 0.0f, 0.0f);

        // Cara inferior
        gl.glTexCoord2f(0.2508f, 0.5014f); // Magenta
        gl.glVertex3f(0.0f, -s, 0.0f);
        gl.glTexCoord2f(0.7278f, 0.5014f);
        gl.glVertex3f(0.0f, 0.0f, -s);
        gl.glTexCoord2f(0.4868f, 0.7472f);
        gl.glVertex3f(-s, 0.0f, 0.0f);

        gl.glTexCoord2f(0.0131f, 0.7463f); // Cian
        gl.glVertex3f(0.0f, -s, 0.0f);
        gl.glTexCoord2f(0.4868f, 0.7472f);
        gl.glVertex3f(-s, 0.0f, 0.0f);
        gl.glTexCoord2f(0.2508f, 0.5014f);
        gl.glVertex3f(0.0f, 0.0f, s);

        gl.glTexCoord2f(0.9639f, 0.7472f); // Púrpura
        gl.glVertex3f(0.0f, -s, 0.0f);
        gl.glTexCoord2f(0.7278f, 0.5014f);
        gl.glVertex3f(0.0f, 0.0f, s);
        gl.glTexCoord2f(0.4868f, 0.7472f);
        gl.glVertex3f(s, 0.0f, 0.0f);

        gl.glTexCoord2f(0.7278f,0.9950f); // Café
        gl.glVertex3f(0.0f, -s, 0.0f);
        gl.glTexCoord2f(0.4868f, 0.7472f);
        gl.glVertex3f(s, 0.0f, 0.0f);
        gl.glTexCoord2f(0.9639f, 0.7472f);
        gl.glVertex3f(0.0f, 0.0f, -s);

        gl.glEnd();

        texture.disable();
        gl.glFlush();
        
        gl.glPopMatrix();
        spinDisplay();
    }
    
    public void reshape(GLAutoDrawable drawable, int x, int y, int w, int h) {
        GL gl = drawable.getGL();
        gl.glViewport(0, 0, w, h);
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        float aspect = (float) w / (float) h;
        gl.glFrustum(-1.0, 1.0, -1.0, 1.0, 1.5, 20.0);
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();
    }
    
    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged,
            boolean deviceChanged) {
    }
    
    private void spinDisplay() {
        spin = spin + spinDelta;
        if (spin > 360f)
            spin = spin - 360;
    }
    
    public void keyTyped(KeyEvent key) {
    }
    
    public void keyPressed(KeyEvent key) {
        switch (key.getKeyCode()) {
            case KeyEvent.VK_ESCAPE:
                super.runExit();
            default:
                break;
        }
    }
    
    public void keyReleased(KeyEvent key) {
    }
    
    public void mouseClicked(MouseEvent key) {
    }
    
    public void mousePressed(MouseEvent mouse) {
        switch (mouse.getButton()) {
            case MouseEvent.BUTTON1:
                spinDelta = 2f;
                break;
            case MouseEvent.BUTTON2:
            case MouseEvent.BUTTON3:
                spinDelta = 0f;
                break;
        }//
        
        super.refresh();
    }
    
    public void mouseReleased(MouseEvent mouse) {
    }
    
    public void mouseEntered(MouseEvent mouse) {
    }
    
    public void mouseExited(MouseEvent mouse) {
    }
    
}
