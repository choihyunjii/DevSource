import styles from '../createTableStyle.module.css';
import React, { useState } from 'react';

export default function DataTypeUI() {
    const [selectedRow, setSelectedRow] = useState(null);

    const handleRowClick = (rowData) => {
        setSelectedRow(rowData);
    };

    return (
        <div>
            <div className={styles.types}>
                <table>
                    <tbody>
                    <tr onClick={() => handleRowClick('Int')}>
                        <td>Int</td>
                    </tr>
                    <tr onClick={() => handleRowClick('char')}>
                        <td>char</td>
                    </tr>
                    <tr onClick={() => handleRowClick('varchar')}>
                        <td>varchar</td>
                    </tr>
                    </tbody>
                </table>
            </div>
            {selectedRow && <div>{selectedRow}</div>}
        </div>
    );
}
