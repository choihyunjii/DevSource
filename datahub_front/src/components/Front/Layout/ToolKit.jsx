import React from "react";
import Tools from "./Generator/Tools";
import DataSet from "./Generator/DataSet";

export default function Toolkit() {
    return (
        <div className="d-flex justify-content-start">
            <Tools/>
            <DataSet/>
        </div>
    )
}
