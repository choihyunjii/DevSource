export default function SmallTitleUI({title,className}){
    return(
        <div style={{display:"flex", justifyContent:"center" ,gap:"90px", width:"70%",marginLeft:"120px"}}>
            <h4 className={className}>{title}</h4>
            <div style={{padding:"10px"}}>2명</div>
        </div>
    )
}