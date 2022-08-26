package com.company;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main {
    public static void main(String[] args){
        File f;

        //일단 디렉토리 내에 있는 파일들을 리스트로 받아와야 한다.
        // 그다음 iterator로 renameFile에 값을 집어 넣는다.
        //이때  _ 라는 글자를 만날때까지 읽다가 그 이후의 값을 변경한다.
        //정말 좋은 방법은 엑셀파일에 호선명, 아세이 정보들을 넣어놓고 CSV 파일로 읽어서 그 값을 이름으로 쓰는것이다.
        // 사실상 나는 Ctrl + H를 구현해야 하는 입장

        //version 1 1_2 같은 형태의 파일을 cam1_HNXXXX_XX_XXX 의 형태로 변형
        //version 2 콘솔창에서 경로와 파일명을 입력받아 처리
        //version 3 GUI 프로그램을 이용해 Ctrl + H 같은 팝업창을 입력박스 형태로 구현



        //이것도 파일패스창 열어서 선택할수 있게 해야힐끼?
        File dir = new File("C:\\Users\\USER\\Desktop\\실사이미지 확보\\8월 8일");
        applyFile(dir);

    }
    public static void applyFile(File f){
        File files[] = f.listFiles();
        String fileName = new String();

        
        for (int i = 0; i < files.length; i++) {
            fileName = files[i].toString();

            fileName=fileName.replace("-","_");

            renameFileAfter8_10(fileName);
        }
    }

    public static void replaceslash(File f){
        File files[] = f.listFiles();
        String fileName = new String();

        File file;
        File fileNew;

        for (int i = 0; i < files.length; i++) {
            fileName = files[i].toString();
            file = new File(fileName);

            fileName=fileName.replace("-","_");

            fileNew = new File(fileName);
            if(file.exists()) {
                System.out.println(fileName);
                file.renameTo(fileNew);
            }
            //renameFileAfter8_10(fileName);
        }
    }

    public void renameFile(String filename, String newFilename) {
        File file = new File( filename );
        File fileNew = new File( newFilename );
        if( file.exists() ) file.renameTo( fileNew );
    }
    // 이렇게 하지 마시고 csv나 txt파일에 읽는 값을 읽어서 배열에 넣어주세요
    //정확히는 GUI 창을 만들고 16개의 입력칸을 만들고 파일패스에서  csv파일을 읽어서 파일에 기록된 블록명을 순서대로 입력창에 불러온다.
    //불러온 입력창에서 수정이 필요한 부분은 수정할 수 있도록 해주고 적용 버튼을 누르면 파일명이 짜잔하고 바뀌게 만든다.
    public static void renameFileAfter8_10(String fileName){
        String[] blockName = new String[17];
        blockName[0]= "none";
        blockName[1] = "HN5074_B51S_SS2";
        blockName[2] = "HN5074_F41S_SS1";
        blockName[3] = "HN5074_F41P_SS2";
        blockName[4] = "HN5074_A21P_SS1";
        blockName[5] = "HN5074_E32S_SS1";
        blockName[6] = "none";
        blockName[7] = "HN5074_A31S_SS1";
        blockName[8] = "HN5074_E52P_SS1";
        blockName[9] = "HN5074_E31P_SS1";
        blockName[10] ="HN5074_A22C_ET1";
        blockName[11] ="HN5074_B51P_SS1";
        blockName[12] ="HN5074_F41S_SS2";
        blockName[13] ="HN5072_A31P_SS1";
        blockName[14] ="HN5072_E32P_SS1";
        blockName[15] ="HN5074_F41P_SS1";
        blockName[16] ="HN5074_E31S_SS1";

        int lastIndex = fileName.lastIndexOf("\\");
        //파일의 경로 부분
        String preFileName = fileName.substring(0,lastIndex+1);
        //파일명이 될 부분
        String subFileName = fileName.substring(lastIndex+1);

        String str_index = new String();
        String newFileName = new String();
        //내 생각엔 파일 클래스를 그대로 가져오는게 맞다고 생각하는데 여기서 재 선언할 필요 없이
        File file = new File(fileName);
        File fileNew;


        //앞의 cam이 있는지 없는지 체크하는 조건 만들고 2_4 왜 적용 안되는지 보기 8/18
        for(int i=blockName.length-1;i>0;i--){
            str_index = Integer.toString(i);
            if(subFileName.endsWith(str_index+".png")){


                if(subFileName.startsWith("cam")){
                    newFileName = preFileName + replaceLast(subFileName,
                            str_index + ".png", blockName[i+1] + ".png");
                    System.out.println(newFileName);
                }else {
                    newFileName = preFileName + "cam" + replaceLast(subFileName,
                            str_index + ".png", blockName[i] + ".png");
                    System.out.println(newFileName);
                }

                fileNew = new File(newFileName);
                file.renameTo(fileNew);
                break;
            }
        }



    }// renameFileAfter8_10 end

    //문자열fileNameStr 의 마지막부분에 preStr이 있다면 preStr을 replaceStr 대체하는 함수
    public static String replaceLast(String fileNameStr, String preStr, String replaceStr ){
        int replaceIndexOf =  fileNameStr.lastIndexOf(preStr);
        if(replaceIndexOf == -1){
            return fileNameStr;
        }else {
            String subFileName = fileNameStr.substring(0, replaceIndexOf);

            //상당히 미스테리인 친구이다. trim도 replace도 안먹어서 억지로 쓴 코드인데 걱정이 된다.
            if(subFileName.endsWith(" ")){
                char a = subFileName.charAt(0);
                subFileName = Character.toString(a);
            }


            //이거 삼항연산자로 바꿔주라 불편하다.
            if (subFileName.endsWith("_")) {
                return subFileName + replaceStr;
            } else {
                return subFileName + "_" + replaceStr;
            }
        }

    }

}//Main end