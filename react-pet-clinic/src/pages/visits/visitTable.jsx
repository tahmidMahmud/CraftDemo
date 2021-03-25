import React from "react";
import EnhancedTable from "../../components/table/enhancedTable";

const title = "Visits";
const headCells = [
  {
    id: "id",
    label: "ID#",
  },
  { id: "vet", label: "Vet" },
  { id: "pet", label: "Pet" },
  { id: "startDate", label: "Begin" },
  { id: "endDate", label: "End" },
  { id: "description", label: "Description" },
];

export default function VisitTable({
  visits,
  vets,
  pets,
  setOpen,
  onUpdateForm,
  onDelete,
}) {
  const rows = visits.map((visit) => {
    const row = {};
    const vet = vets.filter((vet) => vet.id === visit.vet.id)[0];
    const pet = pets.filter((pet) => pet.id === visit.pet.id)[0];
    row.id = visit.id;
    row.vet = vet.firstName + " " + vet.lastName;
    row.pet = pet.name;
    row.startDate = new Date(visit.startDate).toString();
    row.endDate = new Date(visit.endDate).toString();
    row.description = visit.description;
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
