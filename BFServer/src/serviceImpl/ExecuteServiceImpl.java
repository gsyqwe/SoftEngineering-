//璇蜂笉瑕佷慨鏀规湰鏂囦欢鍚?
package serviceImpl;


import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import service.ExecuteService;
import service.UserService;

public class ExecuteServiceImpl implements ExecuteService {
	public String execute(String code, String param) throws RemoteException {
		//如果有ook就先进行转换
		if(code.contains("Ook")){
			String s1="";
			String s2="";
		 	for(int i=0;i<(code.length()-1);i=i+10){
		 		s1=code.substring(i,i+9);
		 		if(s1.equals("Ook. Ook?")){
		 			s1=">";
		 		}else if(s1.equals("Ook? Ook.")){
		 			s1="<";
		 		}else if(s1.equals("Ook. Ook.")){
		 			s1="+";
		 		}else if(s1.equals("Ook! Ook!")){
		 			s1="-";
		 		}else if(s1.equals("Ook! Ook.")){
		 			s1=".";
		 		}else if(s1.equals("Ook. Ook!")){
		 			s1=",";
		 		}else if(s1.equals("Ook! Ook?")){
		 			s1="[";
		 		}else if(s1.equals("Ook? Ook!")){
		 			s1="]";
		 		}
		 		s2=s2+s1;
		 	}
		 	code=s2;
		}
		 String s="";
		 ArrayList<Integer> finalResult=new ArrayList<Integer>(); //保存结果
	     ArrayList<Integer> mr=new ArrayList<Integer>();//过程结果
	     ArrayList<Integer> mfl=new ArrayList<Integer>();//过程中判错
	     char[]cr=new char[param.length()];
	     cr=param.toCharArray();
	     int count10=0;
	     int mdn=0;//过程集合的下标
	     String temp=code;
	     char[] in=temp.toCharArray();
	     mfl.add(1);
	     int len=in.length;
	     boolean flag=true;
	        mr.add(0);
	        for(int i=0;i<len;i++)  
	        {  
	            switch(in[i])  //读入代码并对相应代码进行判断
	            {  
	            case '>':  
                { 
                	//下标加一并进行判断
                    mdn++;
                    if(mdn>(mfl.size()-1)){
                    	mfl.add(0);
                    }
                    if(mdn>(mr.size()-1)){
                    	mr.add(0);
                    }
                    break;  
                }  
                case '<':  
                { //左移
                    mdn--;  
                    if(mdn<0)  
                    {  
                       s="can not leftshift"; 
                        flag=false;
                    }  
                    break;  
                }  
	           case ',':
	            {
	            	if(mdn>(mr.size()-1)){
	            		mr.add(0);
	            	}
	            	mr.set(mdn,(int)(cr[count10]));
	            	count10++;
	            	break;
	            }
	           case '.':  
               {   
                   finalResult.add(mr.get(mdn));   
                   break;  
               }  
	                case '+':  
	                {  
	                    mr.set(mdn, mr.get(mdn)+1);  
	                    break;  
	                }  
	                case '-':  
	                {
	                    mr.set(mdn, mr.get(mdn)-1);  
	                    break;  
	                }  
	                case '[':  
	                { 
	                	//进行循环
	                	if(mdn>(mfl.size()-1)){
	                		mfl.add(1);
	                	}
	                    mfl.set(mdn,1);
	                    break;  
	                }  
	                case ']':  
	                { //注意嵌套循环
	                	 if(mfl.get(mdn)!=1||mr.get(mdn)<0)  
	                     {  //缺少[
	                         s=("error input");
	                         flag=false;  
	                     }  else{
	                        if(mr.get(mdn)>0)
	                        {  
	                            int count1=1;  
	                            while(count1!=0)  
	                            {  
	                                i--;  
	                                if(i<0)  
	                                {  
	                                    i=len;  
	                                    break;  
	                                } 
	                                if(in[i]=='['){ 
	                                	count1--;
	                                } 
	                                else if(in[i]==']'){
	                                	count1++;  
	                                }
	                            }  
	                        }else{  
	                            mfl.set(mdn,0);  
	                        }  
	                	}
	                    break;  
	            }
	                default:  
	                { //因为有其他字符
	                    flag=false;  
	                    s="wrong input";  
	                    break;  
	                }  
	            }  
	            if(flag==false)i=len;//跳出循环  
	        }  
	        if(flag==true)  
	        {  
	            //将结果以char[]类型输出  
	        	for(Integer i:finalResult){
	        	s=s+String.valueOf((char)(i+0));
	        	}	
	        }  
	     return s;
	}
}
