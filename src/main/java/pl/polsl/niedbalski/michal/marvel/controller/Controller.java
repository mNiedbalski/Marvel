package pl.polsl.niedbalski.michal.marvel.controller;

import pl.polsl.niedbalski.michal.marvel.model.LogicalOperations;
import pl.polsl.niedbalski.michal.marvel.model.MainFunctionalityMethods;
import pl.polsl.niedbalski.michal.marvel.view.UserInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author Michał Niedbalski
 * @version 3.0
 * Class handling connection between model and view in MVC pattern.
 */
public class Controller extends JPanel
        implements ActionListener {
    private final LogicalOperations logicalOperations;
    private final UserInterface userInterface;
    private final DisplayingAffiliation displayingAffiliation;
    static String firstOption = "Strongest hero";
    static String secondOption = "Hero affiliation";
    static String thirdOption = "Pearson's Correlation";
    public LogicalOperations getLogicalOperations() {return this.logicalOperations;}
    public UserInterface getUserInterface() {return this.userInterface;}
    /**
     * Constructor of MainGUI that initializes all buttons that user can press.
     * In this method everything is prepared to be later displayed in interface part.
     * @param passedParams passed parameters to the program.
     */
    public Controller(String[] passedParams) {
        super(new BorderLayout());
        logicalOperations = new LogicalOperations();
        userInterface = new UserInterface();
        prepareProgram(passedParams);
        displayingAffiliation = new DisplayingAffiliation(getLogicalOperations().getDatabase(), getLogicalOperations().getUniverses());
        JRadioButton strongestHeroButton = new JRadioButton(firstOption);
        strongestHeroButton.setMnemonic(KeyEvent.VK_B);
        strongestHeroButton.setActionCommand(firstOption);
        strongestHeroButton.setSelected(true);

        JRadioButton heroAffiliationButton = new JRadioButton(secondOption);
        heroAffiliationButton.setMnemonic(KeyEvent.VK_C);
        heroAffiliationButton.setActionCommand(secondOption);

        JRadioButton pearsonsCorrButton = new JRadioButton(thirdOption);
        pearsonsCorrButton.setMnemonic(KeyEvent.VK_D);
        pearsonsCorrButton.setActionCommand(thirdOption);

        ButtonGroup group = new ButtonGroup();
        group.add(strongestHeroButton);
        group.add(heroAffiliationButton);
        group.add(pearsonsCorrButton);

        strongestHeroButton.addActionListener(this);
        heroAffiliationButton.addActionListener(this);
        pearsonsCorrButton.addActionListener(this);

        JPanel radioPanel = new JPanel(new GridLayout(0, 1));
        radioPanel.add(strongestHeroButton);
        radioPanel.add(heroAffiliationButton);
        radioPanel.add(pearsonsCorrButton);

        add(radioPanel, BorderLayout.LINE_START);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    }
    public void prepareProgram(String[] passedParams) { //TODO: Create selecting file using GUI
        boolean foundFile = false;
        while (!foundFile) {
            try {
                logicalOperations.prepareDatabase(passedParams);
                foundFile = true;
            } catch (FileNotFoundException e) {
                while (!foundFile) {
                    try {
                        logicalOperations.checkIfFileExists();
                        foundFile = true;
                    } catch (NullPointerException exception) {

                    }
                }
            } catch (IOException e) {

            }
        }
    }

    /**
     * Method handling pressing a button in GUI.
     * If first button is pressed, the strongest superhero is displayed.
     * If the second button is pressed, user can browse universes to see which superhero is affiliated with chosen universe.
     * If the third button is pressed, the Pearson's correlation coefficient (between number of types and universes per hero) is displayed.
     * @param e the event to be processed
     */
    @MainFunctionalityMethods
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(firstOption)) {
            JFrame f = new JFrame("Displaying strongest superhero");
            JTextArea area = new JTextArea(userInterface.displaySuperhero(logicalOperations.findWithMostSuperpowers()));
            area.setBounds(10, 30, 250, 250);
            int len = f.getTitle().length();
            Dimension dimension = new Dimension(80+7*len, 100);
            f.setMinimumSize(dimension);
            f.add(area);
            f.pack();
            f.setLayout(null);
            f.setVisible(true);
        }
        else if (e.getActionCommand().equals(secondOption)){
            userInterface.createAndShowTree(logicalOperations.getDatabase(),logicalOperations.getUniverses());
        }
        else if (e.getActionCommand().equals(thirdOption)) {
            JFrame f = new JFrame("Displaying the value Pearson's correlation factor");
            JTextArea area = new JTextArea(userInterface.displayPearsonCorrelation(logicalOperations.calculatePearsonCorrelation()));
            int len = f.getTitle().length();
            Dimension dimension = new Dimension(80+7*len, 100);
            f.setPreferredSize(dimension);
            f.add(area);
            f.pack();
            f.setLayout(null);
            f.setVisible(true);
        }
    }

}

