package view_controller;

/**
 * This event driven program allows the user to manage a 
 * movie queue with priorities can be changed in several ways.
 * This code depends on a concrete implementation of 
 * interface PriorityList<E>.  This is javax.swing, not JavaFX.
 * 
 * Do not run this until PriorityLIst<T> is completely tested.
 * You will get a runtime error in JList.java
 * 
 * @author Rick Mercer
 */
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import model.LinkedPriorityList;
import model.Movie;
import model.PriorityList;

public class PriorityListGUI extends JFrame {

  // Need to have the PriorityList interface present and
  // and implementation of this ADT named PriorityListArray
  // before running this as a Java Application.
  private static PriorityList<Movie> model;

  public static void main(String[] args) {
    // Set up data 
    model = new LinkedPriorityList<Movie>();
    Movie m1 = new Movie("Green Book", 3);
    Movie m2 = new Movie("The Shape of Water", 4);
    Movie m3 = new Movie("Moonlight", 4);
    Movie m4 = new Movie("Birdman", 3);
    Movie m5 = new Movie("12 Years a Slave", 5);
    Movie m6 = new Movie("The Bourne Shell", 4);  
    model.insertElementAt(0, m1);
    model.insertElementAt(1, m2);
    model.insertElementAt(2, m3);
    model.insertElementAt(3, m4);
    model.insertElementAt(4, m5);
    model.insertElementAt(5, m6);

    PriorityListGUI window = new PriorityListGUI();
    window.setVisible(true);
  }

  private JList<Object> view;
  private JLabel sizeLabel = new JLabel("Current size: 0");
  private JLabel topPriorityLabel = new JLabel("Top Priority");
  private JButton addButton = new JButton("Add below");
  private JButton removeButton = new JButton("  Remove ");
  private JButton upButton = new JButton(" Move up ");
  private JButton downButton = new JButton("Move down");
  private JButton moveToLast = new JButton("Make last");
  private JButton moveToTop = new JButton("Make top");

  private static final long serialVersionUID = 1L;

  public PriorityListGUI() {
    setTitle("Movie Priority List");
    setSize(460, 300);
    setResizable(false);
    setLocation(200, 200);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    view = new JList<Object>(model.toArray());

    Container topPanel = new JPanel();
    topPanel.setLayout(new GridLayout(2, 1, 4, 4));
    topPanel.add(topPriorityLabel);
    topPanel.add(sizeLabel);

    // Add the buttons in flow layout. This means the window
    // will layout components left to right and top to bottom
    Container buttonPanel = new JPanel();
    buttonPanel.setLayout(new GridLayout(6, 1, 8, 8));
    buttonPanel.add(addButton);
    buttonPanel.add(removeButton);
    buttonPanel.add(upButton);
    buttonPanel.add(downButton);
    buttonPanel.add(moveToTop);
    buttonPanel.add(moveToLast);

    // Add the view (a JList that will show the contents of model)
    // to a panel with scrollbars that are visible as needed.
    view.setFixedCellHeight(20);
    view.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    if (model.size() != 0)
      view.setSelectedIndex(0);
    JScrollPane scrollPanel = new JScrollPane(view);

    scrollPanel.setPreferredSize(new Dimension(370, 220));
    this.getContentPane().add(topPanel, BorderLayout.NORTH);
    setTopLabels();
    this.getContentPane().add(buttonPanel, BorderLayout.EAST);
    this.getContentPane().add(scrollPanel, BorderLayout.CENTER);

    // Register the mediators to listen to a particular button
    removeButton.addActionListener(new RemoveListener());
    addButton.addActionListener(new AddListener());
    upButton.addActionListener(new UpButtonListener());
    downButton.addActionListener(new DownButtonListener());
    moveToTop.addActionListener(new MoveToFrontListener());
    moveToLast.addActionListener(new MoveToEndListener());
  }

  private class MoveToFrontListener implements ActionListener {
    public void actionPerformed(ActionEvent ae) {
      int index = view.getSelectedIndex();
      if (index > 0) {
        model.moveToTop(index);
        view.setListData(model.toArray());
        view.setSelectedIndex(0);
        setTopLabels();
      }
    }
  } // End class AddListener

  private class MoveToEndListener implements ActionListener {
    public void actionPerformed(ActionEvent ae) {
      int index = view.getSelectedIndex();
      if (index >= 0 && index < model.size() - 1) {
        model.moveToLast(index);
        view.setListData(model.toArray());
        view.setSelectedIndex(model.size() - 1);
        setTopLabels();
      }
    }
  } // End class MoveToEndListener

  private class AddListener implements ActionListener {
    public void actionPerformed(ActionEvent ae) {
      Object movieTitle = JOptionPane.showInputDialog("Enter Movie Title");
      if(movieTitle == null)
        return;
      int rating = 0;
      do {
        String ratingAsString = 
            JOptionPane.showInputDialog("Enter rating: 1, 2, 3, 4, or 5");
        if(ratingAsString == null)
          return;
        try {
           rating = Integer.parseInt(ratingAsString);
        }
        catch ( NumberFormatException nfe_)  {
          
        }
      } while (rating < 1 || rating > 5);

      // Don't add empty strings
      if (movieTitle != null && ((String)movieTitle).length() > 0) { 
        // The dialog box had a string with at least one character
        int index = -1;
        if (model.size() == 0)
          index = 0;
        else
          index = view.getSelectedIndex() + 1;

        model.insertElementAt(index, new Movie((String)movieTitle, rating));
        view.setListData(model.toArray());
        view.setSelectedIndex(index);

        setTopLabels();
      }
    }
  } // End class AddListener

  private class RemoveListener implements ActionListener {
    public void actionPerformed(ActionEvent ae) {
      // Remove the currently selected item from the JList
      int index = view.getSelectedIndex();
      if (index >= 0) { // Some element was highlighted
        boolean removingLastElement = (index == model.size() - 1);
        model.removeElementAt(index);
        view.setListData(model.toArray());
        if (model.size() > 0) {
          if (removingLastElement)
            view.setSelectedIndex(index - 1);
          else
            view.setSelectedIndex(index);
        }
        setTopLabels();
      }
    }
  } // End class RemoveListener

  // Whatever code you write in the actionPerformed method will
  // execute every time a user clicks the Move Down button.
  private class DownButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent ae) { // Write the code to move the
      // currently selected item
      // down one spot.
      int index = view.getSelectedIndex();
      if (view.getSelectedIndex() >= 0) {
        if (model.size() > 0) {
          model.lowerPriorityOf(index);
          view.setListData(model.toArray());
          if (index < model.size() - 1)
            view.setSelectedIndex(index + 1);
          else
            view.setSelectedIndex(index);
        }
      }
      setTopLabels();
    }
  } // End class DownButtonListener

  private class UpButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent ae) {
      // Move the currently selected item to a position one index to the left
      int index = view.getSelectedIndex();
      if (view.getSelectedIndex() >= 0) {
        if (model.size() > 0) {
          model.raisePriorityOf(index);
          view.setListData(model.toArray());
          if (index > 0)
            view.setSelectedIndex(index - 1);
          else
            view.setSelectedIndex(index);
        }
      }
      setTopLabels();
    }
  } // End class UpButtonListener

  private void setTopLabels() {
    sizeLabel.setText("Current size: " + model.size());
    if (model.size() > 0) {
//      System.out.println(model.toString());
//      System.out.println(model.getElementAt(0).toString());
//      System.out.println(model.getElementAt(0).getTitle());
        topPriorityLabel.setText("Top Priority: "
          + model.getElementAt(0).getTitle());
    }
   else
      topPriorityLabel.setText("Nothing to do");

    // Show element when it moves out of the visible scroll area.
    view.ensureIndexIsVisible(view.getSelectedIndex());
  }

} // End class PriorityListGUI
