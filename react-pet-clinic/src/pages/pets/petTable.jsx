import React from "react";
import EnhancedTable from "../../components/table/enhancedTable";

const headCells = [
  {
    id: "id",
    label: "ID#",
  },
  { id: "name", label: "Name" },
  { id: "birthDate", label: "Birthday" },
  { id: "type", label: "Animal" },
];

export default function PetTable({
  title,
  pets,
  setOpen,
  onUpdateForm,
  onDelete,
}) {
  return (
    <EnhancedTable
      rows={pets}
      title={title}
      headCells={headCells}
      setOpen={setOpen}
      onDelete={onDelete}
      onUpdateForm={onUpdateForm}
    ></EnhancedTable>
  );
}
