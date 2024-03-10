export default function SmallTitleUI({title,className}){
    return(
        <div style={{display:"flex", justifyContent:"center" ,gap:"100px",marginLeft:"35%", width:"68%"}}>
            <h4 className={className} >{title}</h4>
            <div style={{padding:"10px"}}>2명</div>
        </div>
    )
}