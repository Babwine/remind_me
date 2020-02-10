package pfe.remindme.data;

import android.net.Uri;

import androidx.room.Ignore;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import pfe.remindme.R;
import pfe.remindme.data.repository.notedisplay.NoteDisplayRepository;

public class Note {
    int id;
    String content;
    List<String> tags;

    private static String empty_words = "afin ah ai aie aient aies ait al allô alors apr as attendu au aucun aucune aucunes aucuns auquel aura aurai auraient aurais aurait auras aurez auriez aurions aurons auront autant autre autres aux auxdites auxdits auxquelles auxquels avaient avais avait avant avec avez aviez avions avoir avons ayant ayante ayantes ayants ayez ayons aïe bah basta beaucoup bernique bien bigre bis bof boum bravissimo bravo car ce ceci cela celle celles celui cependant certaines certains ces cet cette ceux chacun chacune chaque chez chic chiche chouette chut ciao clac clic comme comment concernant contre corbleu coucou couic crac cric crénom da dans de debout depuis des desdites desdits desquelles desquels devaient devais devait devant devante devantes devants devez deviez devions devoir devons devra devrai devraient devrais devrait devras devrez devriez devrions devrons devront dia diantre dois doit doive doivent doives donc dont du dudit due dues duquel durant durent dus dussent dut dès dû dût eh elle elles en encontre endéans entre envers es et eu eue eues euh eurent eurêka eus eusse eussent eusses eussiez eussions eut eux excepté eûmes eût eûtes fi fichtre fors fouchtra furent fus fusse fussent fusses fussiez fussions fut fûmes fût fûtes grâce ha hein hem hep heu ho holà hop hormis hors hou hourra hue hum hurrah hé il ils jarnicoton je jusque la ladite laquelle le ledit lequel les lesdites lesdits lesquelles lesquels leur leurs lorsque lui là ma made mais malgré mazette me merci merde mes mien mienne miennes miens miséricorde moi moins mon morbleu motus moyennant mâtin na ne ni nonobstant nos notre nous nul nulle nulles nuls nôtre nôtres oh ohé olé on ont or ou ouais ouf ouille oust ouste outre où palsambleu pan par parbleu parce pardi pardieu parmi pas patatras pechère pendant peu peuchère peut peuvent peux plein plouf plus plusieurs pouah pour pourquoi pourra pourrai pourraient pourrais pourrait pourras pourrez pourriez pourrions pourrons pourront pourvu pouvaient pouvais pouvait pouvant pouvante pouvantes pouvants pouvez pouviez pouvions pouvoir pouvons psitt pst pu pue pues puis puisque puisse puissent puisses puissiez puissions purent pus pussent put pécaïre pût qq qqch qqn quand quant que quel quelle quelles quelqu'un quelqu'une quels qui quiconque quoi quoique rataplan revoici revoilà rien sa sacristi sans saperlipopette sapristi sauf se selon sera serai seraient serais serait seras serez seriez serions serons seront ses si sien sienne siennes siens sinon soi soient sois soit sommes son sont sous soyez soyons stop suis sur ta tandis tant taratata tayaut taïaut te tel telle telles tels tes tien tienne tiennes tiens toi ton touchant tous tout toute toutes tu tudieu turlututu un une unième unièmes v'lan va vers veuille veuillent veuilles veuillez veuillons veulent veut veux via vivement vlan voici voilà vos votre voudra voudrai voudraient voudrais voudrait voudras voudrez voudriez voudrions voudrons voudront voulaient voulais voulait voulant voulante voulantes voulants voulez vouliez voulions vouloir voulons voulu voulue voulues voulurent voulus voulussent voulut voulût vous vu vôtre vôtres zut à ç'a ç'aura ç'aurait ç'avait ça çà étaient étais était étant étante étantes étants étiez étions évohé évoé êtes être ô ôté"
    ;

    public Note(int id, String content, List<String> tagList) {
        this.id = id;
        this.content = content;
        this.tags = tagList;
    }

    @Ignore
    public Note(String content) {
        this.content = content;
        this.tags = new ArrayList<>();
        String[] tmp = content.split(" ");



        for (String word : tmp) {
            if (!empty_words.contains(word.toLowerCase()))
                tags.add(word);
        }
    }

    public static List<String> getInhibitedWordsList(String path) {
        List<String> res = new ArrayList<>();
        File file = new File(path);
        File[] tmpfile = file.listFiles();
        System.out.println("hi");
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String[] tmp = br.readLine().split(" ");
            for (int i=0; i<tmp.length; i++) {
                tmp[i] = tmp[i].toLowerCase();
            }
            res = Arrays.asList(tmp);

        } catch (IOException e) {
            System.err.println(path);
            e.printStackTrace();
        }

        return res;
    }



    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Note) {
            if (((Note) other).getContent().equals(this.getContent())) {
                return true;
            } else return false;
        } else return false;
    }
}
