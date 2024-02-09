import javax.sound.midi.*;
import java.util.Scanner;

import static javax.sound.midi.ShortMessage.*;

public class MusicApp {
    public static void main(String[] args) {
        MusicApp app = new MusicApp();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Podaj numer instrumentu:");
        int instrument = scanner.nextInt();

        System.out.println("Podaj numer nuty:");
        int note = scanner.nextInt();

        app.play(instrument, note);

        scanner.close();
    }

    public void play(int instrument, int note) {
        try {
            Sequencer sequencer = MidiSystem.getSequencer();
            sequencer.open();

            Sequence seq = new Sequence(Sequence.PPQ, 4);

            Track track = seq.createTrack();

            ShortMessage msg1 = new ShortMessage();
            msg1.setMessage(PROGRAM_CHANGE, 1, instrument, 0);
            MidiEvent changeInstrument = new MidiEvent(msg1, 1);
            track.add(changeInstrument);

            ShortMessage msg2 = new ShortMessage();
            msg2.setMessage(NOTE_ON, 1, note, 100);
            MidiEvent noteBeginning = new MidiEvent(msg2, 1);
            track.add(noteBeginning);

            ShortMessage msg3 = new ShortMessage();
            msg3.setMessage(NOTE_OFF, 1, note, 100);
            MidiEvent noteEnd = new MidiEvent(msg3, 16);
            track.add(noteEnd);

            sequencer.setSequence(seq);
            sequencer.start();

        } catch (MidiUnavailableException | InvalidMidiDataException e) {
            e.printStackTrace();
        }
    }
}
