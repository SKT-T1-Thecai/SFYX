import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class OGanalyse{
    // + * i ( )
    static int[][] judge = {
            {1,-1,-1,-1,1},
            {1,1,-1,-1,1},
            {1,1,0,0,1},
            {-1,-1,-1,0,-1},
            {1,1,0,0,1},

    };
    public static int getIndex(char c)
    {
        switch(c)
        {   case '+':return 0;
            case '*':return 1;
            case 'i':return 2;
            case '(':return 3;
            case ')':return 4;
            default: return 100;
        }
    }
    public static List<Character> stack = new ArrayList<>();
    public static void analyse(char[] str)
    {
        List<Character> L=new ArrayList<>();

        int pos=0;
        char c=str[pos++];
        stack.add(c);
        L.add(c);
        System.out.println("I"+c);
        while(pos<str.length-2)
        {
            c = str[pos];
            if(stack.isEmpty())
            {
                assert L != null;
                L.add(c);
                stack.add(c);
                System.out.println("I"+c);
                pos++;
            }
            else{
                int x,y;
                x=getIndex(stack.get(stack.size()-1));
                y=getIndex(c);
                if(judge[x][y]==1)
                {
                    L= flashBack(L);
                    if(L!=null)
                    System.out.println("R");

                    else  if(L==null)
                    {
                    System.out.println("RE");
                    return;
                    }
                }
                else if(judge[x][y]==-1){
                    assert L != null;
                    L.add(c);
                    stack.add(c);
                    System.out.println("I"+c);
                    pos++;
                }
                else if(judge[x][y]==0)
                {
                    System.out.println("E");
                    return;
                }
            }
        }

        while(true)
        {
            L=flashBack(L);


             if(L==null)
            {
                System.out.println("RE");
                break;
            }
          else   if(L.size()==1&&L.get(0)=='N')
            {
                System.out.println("R");
                break;
            }
            else if(L!=null)
                System.out.println("R");
        }


    }
    public static  List<Character> flashBack(List<Character> l)
    {
        if(l.get(l.size() - 1) =='i')
        {
            l.remove(l.size()-1);
            l.add('N');
            stack.remove(stack.size()-1);
            return l;
        }
        if(l.get(l.size()-1)=='N'&&(l.get(l.size()-2)=='+'||l.get(l.size()-2)=='*')&&l.get(l.size()-3)=='N')
        {
            l=l.subList(0,l.size()-3);
            l.add('N');
            stack.remove(stack.size()-1);
            return l;
        }
        if(l.get(l.size()-1)==')'&&(l.get(l.size()-2)=='N')&&l.get(l.size()-3)=='(')
        {
            l=l.subList(0,l.size()-3);
            l.add('N');
            stack.remove(stack.size()-1);
            return l;
        }
        return null;
    }
    public static void main(String[] args) throws IOException {
        String Path="D:\\IDEA projects\\work10_9\\test.txt";
        Path = args[0];
        File  file=new File(Path);
        FileInputStream f=new FileInputStream(file);
        byte[] buf = new byte[4096];
        int len=0;
        while((len=f.read(buf)) !=-1){
            char[] str=new char[len];
            int pos=0;
            for(int i=0;i<len;i++)
            {
                str[pos++]=(char)buf[i];
            }
            analyse(str);


        }
    }
}
