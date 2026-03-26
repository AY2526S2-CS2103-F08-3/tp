package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.model.tag.TagCombo;

/**
 * Panel containing the list of TagCombos.
 */
public class TagComboPanel extends UiPart<Region> {
    private static final String FXML = "TagComboListPanel.fxml";

    @FXML
    private ListView<TagCombo> tagComboListView;

    /**
     * Creates a {@code TagComboPanel} with the given {@code ObservableList}.
     */
    public TagComboPanel(ObservableList<TagCombo> tagComboList) {
        super(FXML);
        tagComboListView.setItems(tagComboList);
        tagComboListView.setCellFactory(listView -> new TagComboListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of an {@code TagCombo} using an {@code TagComboCard}.
     */
    class TagComboListViewCell extends ListCell<TagCombo> {
        @Override
        protected void updateItem(TagCombo tagCombo, boolean empty) {
            super.updateItem(tagCombo, empty);

            if (empty || tagCombo == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new TagComboCard(tagCombo, getIndex() + 1).getRoot());
            }
        }
    }
}
