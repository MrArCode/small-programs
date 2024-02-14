import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MusicApp {
    private ArrayList<JCheckBox> listOfChoiceCheckboxes;
    private Sequencer sequencer;
    private Sequence sequence;
    private Track track;

    String[] instrumentNames = { "Bass Drum", "Closed Hi-Hat", "Open Hi-Hat", "Acoustic Snare", "Crash Cymbal",
            "Hand Clap", "High Tom", "Hi Bongo", "Maracas", "Whistle", "Low Conga", "Cowbell", "Vibraslap",
            "Low-Mid Tom", "High Agogo", "Open Hi Conga" };

    int[] instruments = { 35, 42, 46, 38, 49, 39, 50, 60, 70, 72, 64, 56, 58, 47, 67, 63 };

    public static void main(String[] args) {
        MusicApp musicApp = new MusicApp();
        musicApp.createGUI();
    }

    public void createGUI() {
        JFrame mainFrame = new JFrame();
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BorderLayout layout = new BorderLayout();
        JPanel backgroundPanel = new JPanel(layout);
        backgroundPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        Box buttonArea = new Box(BoxLayout.Y_AXIS);

        JButton start = new JButton("Start");
        start.addActionListener(e -> createSongTracks());
        buttonArea.add(start);

        JButton stop = new JButton("Stop");
        stop.addActionListener(e -> sequencer.stop());
        buttonArea.add(stop);

        JButton tempoUp = new JButton("Faster");
        tempoUp.addActionListener(e -> changeTempo(1.03f));
        buttonArea.add(tempoUp);

        JButton tempoDown = new JButton("Slower");
        tempoDown.addActionListener(e -> changeTempo(0.97f));
        buttonArea.add(tempoDown);

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> saveFile());
        buttonArea.add(saveButton);

        JButton loadButton = new JButton("Load");
        loadButton.addActionListener(e -> loadFile());
        buttonArea.add(loadButton);

        Box instrumentNamesArea = new Box(BoxLayout.Y_AXIS);
        for (String instrumentName : instrumentNames) {
            JLabel instrumentLabel = new JLabel(instrumentName);
            instrumentLabel.setBorder(BorderFactory.createEmptyBorder(4, 1, 4, 1));
            instrumentNamesArea.add(instrumentLabel);
        }

        backgroundPanel.add(BorderLayout.EAST, buttonArea);
        backgroundPanel.add(BorderLayout.WEST, instrumentNamesArea);

        mainFrame.getContentPane().add(backgroundPanel);

        GridLayout gridLayout = new GridLayout(16, 16);
        gridLayout.setVgap(1);
        gridLayout.setHgap(2);

        JPanel mainPanel = new JPanel(gridLayout);
        backgroundPanel.add(BorderLayout.CENTER, mainPanel);

        listOfChoiceCheckboxes = new ArrayList<>();
        for (int i = 0; i < 256; i++) {
            JCheckBox checkBox = new JCheckBox();
            checkBox.setSelected(false);
            listOfChoiceCheckboxes.add(checkBox);
            mainPanel.add(checkBox);
        }

        configureMIDI();

        mainFrame.setBounds(50, 50, 300, 300);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    private void configureMIDI() {
        try {
            sequencer = MidiSystem.getSequencer();
            sequencer.open();
            sequence = new Sequence(Sequence.PPQ, 4);
            track = sequence.createTrack();
            sequencer.setTempoInBPM(120);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createSongTracks() {
        int[] trackList;

        sequence.deleteTrack(track);
        track = sequence.createTrack();

        for (int i = 0; i < 16; i++) {
            trackList = new int[16];

            int key = instruments[i];

            for (int j = 0; j < 16; j++) {
                JCheckBox checkBox = listOfChoiceCheckboxes.get(j + 16 * i);
                if (checkBox.isSelected()) {
                    trackList[j] = key;
                } else {
                    trackList[j] = 0;
                }
            }
            createTrack(trackList);
            track.add(createMidiEvent(176, 1, 127, 0, 16));
        }

        track.add(createMidiEvent(192, 9, 1, 0, 15));

        try {
            sequencer.setSequence(sequence);
            sequencer.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);
            sequencer.setTempoInBPM(120);
            sequencer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void changeTempo(float factor) {
        float tempoFactor = sequencer.getTempoFactor();
        sequencer.setTempoFactor(tempoFactor * factor);
    }

    private void createTrack(int[] list) {
        for (int i = 0; i < 16; i++) {
            int key = list[i];

            if (key != 0) {
                track.add(createMidiEvent(144, 9, key, 100, i));
                track.add(createMidiEvent(128, 9, key, 100, i + 1));
            }
        }
    }

    public static MidiEvent createMidiEvent(int command, int channel, int one, int two, int tick) {
        MidiEvent event = null;
        try {
            ShortMessage msg = new ShortMessage();
            msg.setMessage(command, channel, one, two);
            event = new MidiEvent(msg, tick);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return event;
    }

    private void saveFile() {
        boolean[] fieldCondition = new boolean[256];
        for (int i = 0; i < 256; i++) {
            JCheckBox fieldChoice = listOfChoiceCheckboxes.get(i);
            if (fieldChoice.isSelected()) {
                fieldCondition[i] = true;
            }
        }
        try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("Composition.ser"))) {
            os.writeObject(fieldCondition);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadFile() {
        boolean[] fieldCondition = null;
        try (ObjectInputStream is = new ObjectInputStream(new FileInputStream("Composition.ser"))) {
            fieldCondition = (boolean[]) is.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 256; i++) {
            JCheckBox fieldChoice = listOfChoiceCheckboxes.get(i);
            fieldChoice.setSelected(fieldCondition[i]);
        }

        sequencer.stop();
        createSongTracks();
    }
}
