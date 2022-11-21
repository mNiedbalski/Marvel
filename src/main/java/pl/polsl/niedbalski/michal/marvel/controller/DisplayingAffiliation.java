package pl.polsl.niedbalski.michal.marvel.controller;

import pl.polsl.niedbalski.michal.marvel.model.MainFunctionalityMethods;
import pl.polsl.niedbalski.michal.marvel.model.Superhero;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Set;

/**
 * @author Michał Niedbalski
 * @version 1.0
 * Class handling process of creating tree object, in which parent nodes are universes and child nodes are superheroes.
 */
public class DisplayingAffiliation extends JPanel
        implements TreeSelectionListener {
    /**
     * Class field representing JTree object.
     */
    private final JTree tree;
    public DisplayingAffiliation(ArrayList <Superhero> superheroes, Set<String> universes) {
        super(new GridLayout(1, 0));
        DefaultMutableTreeNode top =
                new DefaultMutableTreeNode("Universes");
        createNodes(top,superheroes,universes);
        tree = new JTree(top);
        tree.getSelectionModel().setSelectionMode
                (TreeSelectionModel.SINGLE_TREE_SELECTION);
        tree.addTreeSelectionListener(this);

        boolean playWithLineStyle = false;
        if (playWithLineStyle) {
            String lineStyle = "Horizontal";
            System.out.println("line style = " + lineStyle);
            tree.putClientProperty("JTree.lineStyle", lineStyle);
        }
        JScrollPane treeView = new JScrollPane(tree);
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPane.setTopComponent(treeView);
        Dimension minimumSize = new Dimension(100, 50);
        treeView.setMinimumSize(minimumSize);
        splitPane.setDividerLocation(100);
        splitPane.setPreferredSize(new Dimension(500, 300));
        add(splitPane);
    }

    /**
     * Method reacting to events.
     * @param e the event that characterizes the change.
     */
    public void valueChanged(TreeSelectionEvent e) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode)
                tree.getLastSelectedPathComponent();

        if (node == null) return;
    }

    /**
     * Creating nodes in tree using data from database.
     * @param top Top of the tree.
     * @param superheroes Database of superheroes.
     * @param universes Set of unique universes.
     */
    @MainFunctionalityMethods
    private void createNodes(DefaultMutableTreeNode top, ArrayList<Superhero> superheroes, Set<String> universes) {

        DefaultMutableTreeNode category = null;
        DefaultMutableTreeNode element = null;

        for (String universeInScope : universes) {
            category = new DefaultMutableTreeNode(universeInScope);
            top.add(category);
            for (Superhero superheroInScope : superheroes) {
                if (superheroInScope.getUniverses().contains(universeInScope)){
                    element = new DefaultMutableTreeNode(superheroInScope.getCharName());
                    category.add(element);
                }

            }
        }
    }
}