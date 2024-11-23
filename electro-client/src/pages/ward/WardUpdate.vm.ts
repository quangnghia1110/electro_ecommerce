import { useState } from 'react';
import { useForm, zodResolver } from '@mantine/form';
import WardConfigs from 'pages/ward/WardConfigs';
import DistrictConfigs from 'pages/district/DistrictConfigs';
import { WardRequest, WardResponse } from 'models/Ward';
import { DistrictResponse } from 'models/District';
import { SelectOption } from 'types';
import useUpdateApi from 'hooks/use-update-api';
import useGetByIdApi from 'hooks/use-get-by-id-api';
import useGetAllApi from 'hooks/use-get-all-api';
import MiscUtils from 'utils/MiscUtils';

function useWardUpdateViewModel(id: number) {
  const form = useForm({
    initialValues: WardConfigs.initialCreateUpdateFormValues,
    schema: zodResolver(WardConfigs.createUpdateFormSchema),
  });

  const [ward, setWard] = useState<WardResponse>();
  const [prevFormValues, setPrevFormValues] = useState<typeof form.values>();
  const [districtSelectList, setDistrictSelectList] = useState<SelectOption[]>([]);

  const updateApi = useUpdateApi<WardRequest, WardResponse>(WardConfigs.resourceUrl, WardConfigs.resourceKey, id);
  useGetByIdApi<WardResponse>(WardConfigs.resourceUrl, WardConfigs.resourceKey, id,
    (wardResponse) => {
      setWard(wardResponse);
      const formValues: typeof form.values = {
        name: wardResponse.name,
        code: wardResponse.code,
        districtId: String(wardResponse.district.id),
      };
      form.setValues(formValues);
      setPrevFormValues(formValues);
    }
  );
  useGetAllApi<DistrictResponse>(DistrictConfigs.resourceUrl, DistrictConfigs.resourceKey,
    { all: 1 },
    (districtListResponse) => {
      const selectList: SelectOption[] = districtListResponse.content.map((item) => ({
        value: String(item.id),
        label: item.name,
      }));
      setDistrictSelectList(selectList);
    }
  );

  const handleFormSubmit = form.onSubmit((formValues) => {
    setPrevFormValues(formValues);
    if (!MiscUtils.isEquals(formValues, prevFormValues)) {
      const requestBody: WardRequest = {
        name: formValues.name,
        code: formValues.code,
        districtId: Number(formValues.districtId),
      };
      updateApi.mutate(requestBody);
    }
  });

  return {
    ward,
    form,
    handleFormSubmit,
    districtSelectList,
  };
}

export default useWardUpdateViewModel;
