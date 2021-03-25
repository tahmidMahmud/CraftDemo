import React from "react";
import EnhancedTable from "../../components/table/enhancedTable";

const title = "Pet Owners";
const headCells = [
  {
    id: "id",
    label: "ID#",
  },
  { id: "name", label: "Name" },
  { id: "address", label: "Address" },
  { id: "city", label: "City" },
  { id: "telephone", label: "Telephone" },
];

export default function OwnerTable({
  owners,
  setOpen,
  onUpdateForm,
  onDelete,
}) {
  return (
    <EnhancedTable
      rows={owners}
      title={title}
      headCells={headCells}
      setOpen={setOpen}
      onDelete={onDelete}
      onUpdateForm={onUpdateForm}
    ></EnhancedTable>
  );
}
