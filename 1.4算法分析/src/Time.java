import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;
public class Time {
	public void play(){
        try {
            Sequencer player=MidiSystem.getSequencer();
            player.open();
            Sequence seq=new Sequence(Sequence.PPQ,4);//PPQ是Sequence类的一个静态变量，Sequence来组织顺序
            Track track=seq.createTrack();

            ShortMessage a=new ShortMessage();//Message决定干什么
            a.setMessage(144,1,44,100);
            MidiEvent noteOn=new MidiEvent(a,1);// MidiEvent决定什么时候干，这里表示第一排演奏a的内容
            track.add(noteOn);//Track带有全部 MidiEvent对象
            ShortMessage b=new ShortMessage();
            a.setMessage(128,1,44,100);
            MidiEvent notOff=new MidiEvent(b,1);
            track.add(notOff);

            player.setSequence(seq);//将Sequence传送到Sequencez上，根据顺序来播放，同一时间可以播放多个操作
            player.start();

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
	
	public static void main(String[] args){
		long now=System.currentTimeMillis();
		while((System.currentTimeMillis()-now)/60000>=1){
			Time m=new Time();
	        m.play();
		}
	}
}
