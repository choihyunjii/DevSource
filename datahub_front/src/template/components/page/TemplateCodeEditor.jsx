import React, { useState } from 'react';
import AceEditor from 'react-ace';
import 'ace-builds/src-noconflict/mode-html';
import 'ace-builds/src-noconflict/theme-github';
import styles from '../../styleModule/CodeEditorStyle.module.css';
import ButtonUI from "../../../project/components/uI/ButtonUI";
import TitleUI from "../../../project/components/uI/TitleUI";


// 이하 라우팅 및 애플리케이션 설정

function App() {
    const [htmlContent, setHtmlContent] = useState(`<!DOCTYPE html>
    <html lang="en">
    <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>카드 디자인 예제</title>
    <style>
      /* 카드 스타일 */
      .card {
        border: 1px solid #ccc;
        border-radius: 8px;
        padding: 16px;
        margin-bottom: 16px;
        background-color: #fff;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
      }
    
      /* 카드 제목 스타일 */
      .card h2 {
        font-size: 1.5rem;
        margin-bottom: 8px;
      }
    
      /* 카드 내용 스타일 */
      .card p {
        font-size: 1rem;
        color: #555;
      }
    </style>
    </head>
    <body>
    
    <div class="container">
      <div class="card-container">
        <div class="card">
          <h2>카드 1</h2>
          <p>이 카드는 예제입니다.</p>
        </div>
        <div class="card">
          <h2>카드 2</h2>
          <p>또 다른 카드 예제입니다.</p>
        </div>
        <div class="card">
          <h2>카드 3</h2>
          <p>세 번째 카드 예제입니다.</p>
        </div>
      </div>
    </div>
    
    </body>
    </html>
`);

    const runHtml = () => {
        const outputIframe = document.getElementById("output");
        const iframeDocument = outputIframe.contentWindow.document;
        iframeDocument.open();
        iframeDocument.write(htmlContent);
        iframeDocument.close();
    };

    return (
        <div>
            <div className={styles.editorToolBox}>
                <div className={styles.headerContainer}>
                    <TitleUI title={"Create Templeate"}/>
                    <ButtonUI children={"실행"} className={styles.noneButton} onClick={runHtml}/>
                </div>

                <div className={styles.editorBox}>
                    <AceEditor
                        mode="html"
                        theme="github"
                        value={htmlContent}
                        onChange={setHtmlContent}
                        style={{ height: '600px', width: '600px' }}
                    />
                    {/*이거주석처리 지우지마셈 Ifame Title값 원래 설정해야되는데 그거 방지하고 처리하는데 쓸 녀석임!*/}
                     {/*eslint-disable-next-line jsx-a11y/iframe-has-title */}
                    <iframe className={styles.prevViewContainer} id="output"></iframe>
                </div>
                <ButtonUI children={"저장하기"} className={styles.blueButton} onClick={runHtml}/>
            </div>
        </div>
    );
}

export default App;
