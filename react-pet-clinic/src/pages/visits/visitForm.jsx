import React from "react";
import Styles from "../../Stlyes";
import { Form, Field } from "react-final-form";
import ModalForm from "../../components/form/modalForm";
import TextField from "@material-ui/core/TextField";
import AdapterDateFns from "@material-ui/lab/AdapterDateFns";
import LocalizationProvider from "@material-ui/lab/LocalizationProvider";
import DateTimePicker from "@material-ui/lab/DateTimePicker";
import { FORM_ERROR } from "final-form";

const onSubmit = async (values, onAdd, onUpdate, setOpen, updateValues) => {
  let response = updateValues ? await onUpdate(values) : await onAdd(values);
  if (response.status === 409) {
    return {
      [FORM_ERROR]:
        "Visit is conflicting with an exisiting visit. Please reschedule.",
    };
  } else if (response.status !== 200) {
    return {
      [FORM_ERROR]: "Unknown Error Occured. Please try again",
    };
  }
  setOpen(false);
};

function getVetName(vet) {
  return vet.firstName + " " + vet.lastName;
}

const VisitForm = ({
  onAdd,
  updateValues,
  open,
  setOpen,
  onUpdate,
  vets,
  pets,
}) => (
  <ModalForm open={open} setOpen={setOpen}>
    <Styles>
      <h1>Add Visit</h1>
      <Form
        onSubmit={(values) =>
          onSubmit(values, onAdd, onUpdate, setOpen, updateValues)
        }
        initialValues={updateValues ? updateValues : {}}
        render={({ handleSubmit, form, submitting, pristine, submitError }) => (
          <form onSubmit={handleSubmit}>
            {submitError && <div className="error">{submitError}</div>}
            <div>
              <Field name="startDate" value="06/11/1994">
                {(props) => (
                  <div>
                    <LocalizationProvider dateAdapter={AdapterDateFns}>
                      <DateTimePicker
                        label="Begin"
                        name={props.input.name}
                        value={props.input.value}
                        onChange={props.input.onChange}
                        renderInput={(params) => <TextField {...params} />}
                      />
                    </LocalizationProvider>
                  </div>
                )}
              </Field>
              <Field name="endDate" value="06/11/1994">
                {(props) => (
                  <div>
                    <LocalizationProvider dateAdapter={AdapterDateFns}>
                      <DateTimePicker
                        label="End"
                        name={props.input.name}
                        value={props.input.value}
                        onChange={props.input.onChange}
                        renderInput={(params) => <TextField {...params} />}
                      />
                    </LocalizationProvider>
                  </div>
                )}
              </Field>
            </div>
            <div>
              <label>Vet</label>
              {updateValues ? (
                <label>
                  {
                    vets
                      .filter((vet) => vet.id === updateValues.vet.id)
                      .map(getVetName)[0]
                  }
                </label>
              ) : (
                <Field name="vet" component="select">
                  <option />
                  {vets.map((vet) => {
                    return (
                      <React.Fragment>
                        <option value={vet.id}>
                          {vet.firstName + " " + vet.lastName}
                        </option>
                      </React.Fragment>
                    );
                  })}
                </Field>
              )}
            </div>
            <div>
              <label>Pet</label>
              {updateValues ? (
                <label>
                  {pets.filter((pet) => pet.id === updateValues.pet.id)[0].name}
                </label>
              ) : (
                <Field name="pet" component="select">
                  <option />
                  {pets.map((pet) => {
                    return (
                      <React.Fragment>
                        <option value={pet.id}>{pet.name}</option>
                      </React.Fragment>
                    );
                  })}
                </Field>
              )}
            </div>
            <div>
              <label>Description</label>
              <Field
                name="description"
                component="textarea"
                placeholder="Description"
              />
            </div>
            <div className="buttons">
              <button type="submit" disabled={submitting || pristine}>
                {updateValues ? "Modify" : "Submit"}
              </button>
              <button
                type="button"
                onClick={form.reset}
                disabled={submitting || pristine}
              >
                Reset
              </button>
            </div>
          </form>
        )}
      />
    </Styles>
  </ModalForm>
);

export default VisitForm;
