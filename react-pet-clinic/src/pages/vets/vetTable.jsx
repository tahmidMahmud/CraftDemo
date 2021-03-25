import React from "react";
import EnhancedTable from "../../components/table/enhancedTable";

const title = "Vets";
const headCells = [
  {
    id: "id",
    label: "ID#",
  },
  { id: "name", label: "Name" },
  { id: "specialties", label: "Specialties" },
];

export default function VetTable({ vets, setOpen, onUpdateForm, onDelete }) {
  const rows = vets.map((vets) => {
    const row = {};
    row.id = vets.id;
    row.name = vets.firstName + " " + vets.lastName;
    row.specialties = vets.specialties ? vets.specialties.join(", ") : "";
    row.link = vets.link;
    return row;
  });

  return (
    <EnhancedTable
      rows={rows}
      title={title}
      headCells={headCells}
      setOpen={setOpen}
      onUpdateForm={onUpdateForm}
      onDelete={onDelete}
    ></EnhancedTable>
  );
}
