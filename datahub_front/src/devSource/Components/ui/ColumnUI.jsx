import DataUI from "./DataUI";

export default function ColumnUI({ columns }) {
    return (
        <table>
            <thead>
            <tr>
                {columns.map((column, index) => (
                    <th key={index}>{column.name}</th>
                ))}
            </tr>
            </thead>
            <tbody>
            <tr>
                {columns.map((column, index) => (
                    <td key={index}>
                        <DataUI columnID={column.id} />
                    </td>
                ))}
            </tr>
            </tbody>
        </table>
    );
}
